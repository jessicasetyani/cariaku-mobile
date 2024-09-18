package com.styletheory.cariaku.data

import com.styletheory.cariaku.network.ApiRoute
import com.styletheory.cariaku.data.model.request.ChatCompletionRequest
import com.styletheory.cariaku.data.model.response.ChatCompletionResponse
import com.styletheory.cariaku.util.Constant
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.headers

class OpenRouterClient(private val httpClient: HttpClient) {
    suspend fun chatCompletion(chatCompletionRequest: ChatCompletionRequest): ChatCompletionResponse {
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
