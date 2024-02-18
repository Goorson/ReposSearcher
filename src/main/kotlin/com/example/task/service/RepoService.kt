package com.example.task.service

import com.example.task.model.AllReposReturnBody
import org.springframework.stereotype.Service

@Service
interface RepoService {
    suspend fun getAllRepos(username: String): List<AllReposReturnBody>
}