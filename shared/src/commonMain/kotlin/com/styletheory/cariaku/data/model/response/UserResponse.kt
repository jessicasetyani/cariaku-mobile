package com.styletheory.cariaku.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val objectId: String,
    val username: String,
    val sessionToken: String
)