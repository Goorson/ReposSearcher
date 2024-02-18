package com.example.task.model

import kotlinx.serialization.Serializable

@Serializable
data class Branch (
    val name: String,
    val commit: Commit
        ){
}