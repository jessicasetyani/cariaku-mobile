package com.styletheory.cariaku.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val error: Error
)

@Serializable
data class Error(
    val message: String,
    val code: Int
)
