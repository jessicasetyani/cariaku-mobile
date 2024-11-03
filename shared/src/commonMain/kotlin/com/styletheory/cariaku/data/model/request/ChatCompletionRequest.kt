package com.styletheory.cariaku.data.model.request

import com.styletheory.cariaku.data.model.MessageOpenAi
import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionRequest(
    val model: String,
    val messages: List<MessageOpenAi>
)