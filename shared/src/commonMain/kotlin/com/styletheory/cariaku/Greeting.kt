package com.styletheory.cariaku

import com.styletheory.cariaku.network.OpenRouterClient
import com.styletheory.cariaku.network.model.request.ChatCompletionRequest
import com.styletheory.cariaku.util.onSuccess

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    suspend fun chatWithAI(chatRequest:ChatCompletionRequest, client: OpenRouterClient): String {
        val response = client.callOpenRouterChat(chatRequest)
        response.onSuccess {
            println(it.usage.totalTokens.toString())
        }
        return response.toString()
    }
}