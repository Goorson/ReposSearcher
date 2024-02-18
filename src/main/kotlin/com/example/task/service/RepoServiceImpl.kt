package com.example.task.service

import com.example.task.controller.exceptions.UserNotFoundException
import com.example.task.model.Repo
import com.example.task.model.AllReposReturnBody
import org.springframework.stereotype.Service
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired

@Service
class RepoServiceImpl(
    @Autowired val branchService: BranchService
): RepoService {

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json( Json {
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun getAllRepos (username: String): List<AllReposReturnBody> {
        val response = getRepos(username, client)
        return branchService.mapToBranches(response)
    }

    private suspend fun getRepos(username: String, client: HttpClient): List<Repo>{
        try{
            val response: List<Repo> = client.get("https://api.github.com/users/$username/repos") {
                headers {
                    append("Accept",  "application/json")
                    append("X-GitHub-Api-Version",  "2022-11-28")
                }
            }.body()
            return response
        } catch (e: ClientRequestException) {
            throw UserNotFoundException(e.message, e.response.status.value)
        }catch (e: JsonConvertException) {
            throw UserNotFoundException(e.message.orEmpty(), 404)
        }
    }
}