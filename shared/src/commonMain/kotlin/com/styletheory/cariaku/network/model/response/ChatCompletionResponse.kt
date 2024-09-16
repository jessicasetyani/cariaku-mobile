package com.styletheory.cariaku.network.model.response

import com.styletheory.cariaku.network.model.Message
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionResponse(
    val id: String,
    val model: String,
    val created: Long,
    val choices: List<Choice>,
    val usage: Usage? = null
)

@Serializable
data class Choice(
    val logprobs: String? = null,
    @SerialName("finish_reason")
    val finishReason: String,
    val index: Int,
    val message: Message
)

@Serializable
data class Usage(
    @SerialName("prompt_tokens")
    val promptTokens: Int,
    @SerialName("completion_tokens")
    val completionTokens: Int,
    @SerialName("total_tokens")
    val totalTokens: Int
)
