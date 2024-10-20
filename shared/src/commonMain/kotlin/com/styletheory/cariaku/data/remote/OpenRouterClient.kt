package com.styletheory.cariaku.data.remote

import com.styletheory.cariaku.data.model.request.ChatCompletionRequest
import com.styletheory.cariaku.data.model.response.ChatCompletionResponse
import com.styletheory.cariaku.util.Constant
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.headers

class OpenRouterClient(private val httpClient: HttpClient) {
    suspend fun chatCompletion(chatCompletionRequest: ChatCompletionRequest): ChatCompletionResponse {
        try {
            val response = httpClient.post(
                urlString = ApiRoute.baseUrlOpenRouter + "chat/completions"
            ) {
                contentType(ContentType.Application.Json)
                headers {
//                    append(Constant.HEADER_X_TITLE, Constant.PROJECT_NAME_CARI_AKU)
                    append("Authorization", "Bearer ${Constant.OPENROUTER_API_KEY}")
                    append("Helicone-Auth", "Bearer ${Constant.HELICONE_API_KEY}")
                }
                setBody(chatCompletionRequest)
            }
            val chatCompletionResponse = response.body<ChatCompletionResponse>()
            return chatCompletionResponse

        } catch(e: Exception) {
            throw e
        }
    }
}
