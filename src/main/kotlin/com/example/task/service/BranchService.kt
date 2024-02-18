package com.example.task.service

import com.example.task.model.AllReposReturnBody
import com.example.task.model.Branch
import com.example.task.model.Repo
import io.ktor.client.*
import org.springframework.stereotype.Service

@Service
interface BranchService {
//    suspend fun getBranches(owner: String, repo: String, client: HttpClient): List<Branch>
    suspend fun mapToBranches(repos: List<Repo>): List<AllReposReturnBody>
    suspend fun getBranches(owner: String, repo: String): List<Branch>
}