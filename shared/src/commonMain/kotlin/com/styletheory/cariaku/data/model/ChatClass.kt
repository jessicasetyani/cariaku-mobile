package com.styletheory.cariaku.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatClass(
    val content: String,
    @SerialName("sender_type")
    val isActive: Boolean,
    val userProfile: TypePointer,
    val assistant: TypePointer,
    @SerialName("chat")
    val chat: TypePointer,
    @SerialName("last_message_at")
    val timestamp: TypeDate
)