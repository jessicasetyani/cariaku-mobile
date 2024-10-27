package com.styletheory.cariaku.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Assistant(
    val objectId: String,
    val name: String,
    val model: String,
    val language: String,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("custom_prompt")
    val customPrompt: String,
    val createdAt: String,
    val updatedAt: String,
    val category: Pointer,
    @SerialName("assistant_capability")
    val assistantCapability: Pointer
)