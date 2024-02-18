package com.example.task.service

import com.example.task.model.AllReposReturnBody
import com.example.task.model.Branch
import com.example.task.model.Repo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service

@Service
class BranchServiceImpl: BranchService {

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json( Json {
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun mapToBranches(repos: List<Repo>): List<AllReposReturnBody> {
        return repos.filter{
            !it.fork
        }.map {
            AllReposReturnBody(
                it.name,
                it.owner.login,
                getBranches(it.owner.login, it.name),
            )
        }
    }

    override suspend fun getBranches(owner: String, repo: String): List<Branch> {
        val response = makeRequest(owner, repo)
        if (response.status == HttpStatusCode.TooManyRequests) {
            val retryAfter = response.headers["Retry-After"]?.toLongOrNull() ?: 60
            delay(retryAfter * 1000)
            return getBranches(owner, repo)
        }
        return response.body()
    }

    private suspend fun makeRequest(owner: String, repo: String): HttpResponse {
        return client.get("https://api.github.com/repos/$owner/$repo/branches") {
            headers {
                append("Accept", "application/json")
                append("X-GitHub-Api-Version", "2022-11-28")
            }
        }
    }
}