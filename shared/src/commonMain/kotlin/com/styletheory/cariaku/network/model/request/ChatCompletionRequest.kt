package com.styletheory.cariaku.network.model.request

import com.styletheory.cariaku.network.model.Message
import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionRequest(
    val model: String,
    val messages: List<Message>
)