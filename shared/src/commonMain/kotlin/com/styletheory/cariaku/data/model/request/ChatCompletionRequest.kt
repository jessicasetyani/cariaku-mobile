package com.styletheory.cariaku.data.model.request

import com.styletheory.cariaku.data.model.Message
import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionRequest(
    val model: String,
    val messages: List<Message>
)