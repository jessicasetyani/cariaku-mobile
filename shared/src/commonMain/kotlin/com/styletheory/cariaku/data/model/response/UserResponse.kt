package com.styletheory.cariaku.data.model.response

import com.styletheory.cariaku.data.model.PointerSerializer
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val objectId: String,
    val username: String,
    val sessionToken: String,
    val email: String? = null,
    val emailVerified: Boolean = false,
    val userProfile: UserProfilePointer? = null
)

@Serializable
data class UserProfilePointer(
    @Serializable(with = PointerSerializer::class)
    val __type: String = "Pointer",
    val className: String,
    val objectId: String
)