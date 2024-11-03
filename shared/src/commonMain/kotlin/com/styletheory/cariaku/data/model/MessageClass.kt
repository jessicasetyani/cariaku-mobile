package com.styletheory.cariaku.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageClass(
    val content: String,
    @SerialName("is_read")
    val isRead: Boolean,
    @SerialName("total_tokens")
    val totalTokens: Int,
    @SerialName("sender_type")
    val customPrompt: String,
    @SerialName("text_content")
    val textContent: String,
    @SerialName("processed_content")
    val processedContent: String,
    val timestamp: TypeDate,
    val attachments: TypeRelation? = null,
    val chat: TypePointer
)