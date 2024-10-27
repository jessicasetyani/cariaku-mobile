package com.styletheory.cariaku.data.model.response

import com.styletheory.cariaku.data.model.Pointer
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val objectId: String,
    val username: String,
    val sessionToken: String,
    val email: String? = null,
    val emailVerified: Boolean = false,
    val userProfile: Pointer? = null
)