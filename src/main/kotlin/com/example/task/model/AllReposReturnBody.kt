package com.example.task.model

import kotlinx.serialization.Serializable

@Serializable
data class AllReposReturnBody (
    val repositoryName: String = "",
    val ownerLogin: String = "",
    val branches: List<Branch>
)