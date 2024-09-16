package com.styletheory.cariaku.network.model

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
