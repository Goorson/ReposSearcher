package com.example.task.model

import kotlinx.serialization.Serializable

@Serializable
data class Commit (
    val sha: String
    )