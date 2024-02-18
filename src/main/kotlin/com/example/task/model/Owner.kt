package com.example.task.model

import kotlinx.serialization.Serializable


@Serializable
data class Owner(
    val login: String
)