package com.styletheory.cariaku.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val objectId: String,
    val name: String? = null,
    val language: String? = null,
    val gender: String? = null,
    @SerialName("profile_url")
    val profileUrl: String? = null,
    val job: String? = null,
    val nationality: String? = null
)