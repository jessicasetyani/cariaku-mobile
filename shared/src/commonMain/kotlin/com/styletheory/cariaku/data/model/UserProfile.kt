package com.styletheory.cariaku.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val objectId: String,
    val DoB: Dob,
    val name: String,
    val language: String,
    val gender: String,
    val createdAt: String,
    val updatedAt: String
)

@Serializable
data class Dob(
    val __type: String,
    val iso: String
)

@Serializable
data class UserProfileResponse(
    val results: List<UserProfile>
)
