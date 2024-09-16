package com.styletheory.cariaku.network

import com.styletheory.cariaku.network.model.request.ChatCompletionRequest
import com.styletheory.cariaku.network.model.response.ChatCompletionResponse
import com.styletheory.cariaku.util.Constant
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.headers
import kotlinx.coroutines.flow.MutableStateFlow

class OpenRouterClient(private val httpClient: HttpClient) {

    val chatMessages = MutableStateFlow<List<String>>(emptyList())

    suspend fun callOpenRouterChat(chatCompletionRequest: ChatCompletionRequest): ChatCompletionResponse {
        try {
            val response = httpClient.post(
                urlString = ApiRoute.baseUrlOpenRouter + "api/v1/chat/completions"
            ) {
                contentType(ContentType.Application.Json)
                headers {
                    append(Constant.HEADER_X_TITLE, Constant.PROJECT_NAME_CARI_AKU)
                }
                setBody(chatCompletionRequest)
            }

            return response.body<ChatCompletionResponse>()

        } catch(e: Exception) {
            throw e
        }
    }
}
