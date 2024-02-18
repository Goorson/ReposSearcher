package com.example.task.model

import kotlinx.serialization.Serializable

@Serializable
data class Repo(
    val name: String,
    val owner: Owner,
    val fork: Boolean
)