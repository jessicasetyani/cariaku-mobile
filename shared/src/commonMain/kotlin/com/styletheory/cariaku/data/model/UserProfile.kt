package com.styletheory.cariaku.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val objectId: String,
    @SerialName("DoB")
    val dob: Dob,
    val name: String,
    val language: String,
    val gender: String,
    val createdAt: String,
    val updatedAt: String
)

@Serializable
data class Dob(
    @SerialName("__type")
    val type: String,
    val iso: String
)

@Serializable
data class UserProfileResponse(
    val results: List<UserProfile>
)