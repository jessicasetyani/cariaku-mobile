package com.styletheory.cariaku

import com.styletheory.cariaku.network.OpenRouterClient
import com.styletheory.cariaku.network.model.request.ChatCompletionRequest
import com.styletheory.cariaku.network.model.response.ChatCompletionResponse

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    suspend fun chatWithAI(chatRequest: ChatCompletionRequest, client: OpenRouterClient): ChatCompletionResponse {
        val response = client.callOpenRouterChat(chatRequest)
        return response
    }
}