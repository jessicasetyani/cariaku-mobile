package com.styletheory.cariaku.data.model.response

import com.styletheory.cariaku.data.model.Message
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatCompletionResponse(
    val id: String,
    val model: String,
    val created: Long,
    val choices: List<Choice>,
    val usage: Usage
)

@Serializable
data class Choice(
    val logprobs: LogProbs? = null,
    @SerialName("finish_reason")
    val finishReason: String,
    val index: Int,
    val message: Message
)

@Serializable
data class LogProbs(
    val content: List<String> = emptyList(),
    val refusal: List<String> = emptyList()
)

@Serializable
data class Usage(
    @SerialName("prompt_tokens")
    val promptTokens: Int = 0,
    @SerialName("completion_tokens")
    val completionTokens: Int = 0,
    @SerialName("total_tokens")
    val totalTokens: Int = 0
)
