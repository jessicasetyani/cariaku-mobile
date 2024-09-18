package com.styletheory.cariaku.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val role: String,
    val content: String,
    val refusal: String? = ""
)
