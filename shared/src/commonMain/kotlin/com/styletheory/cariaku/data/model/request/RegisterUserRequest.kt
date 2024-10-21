package com.styletheory.cariaku.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserRequest(
    val username: String,
    val password: String
)