package com.styletheory.cariaku.data.remote

import com.styletheory.cariaku.data.model.request.ChatCompletionRequest
import com.styletheory.cariaku.data.model.response.ChatCompletionResponse
import com.styletheory.cariaku.data.remote.ApiRoute.CARI_AKU_BASE_URL
import com.styletheory.cariaku.util.Constant.YOUR_SITE_NAME
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
                urlString = ApiRoute.BASE_URL_OPEN_ROUTER + "/chat/completions"
            ) {
                contentType(ContentType.Application.Json)
                headers {
//                    append("Authorization", "Bearer ${Constant.OPENROUTER_API_KEY}")
//                    append("Helicone-Auth", "Bearer ${Constant.HELICONE_API_KEY}")
                    append("HTTP-Referer", CARI_AKU_BASE_URL)
                    append("X-Title", YOUR_SITE_NAME)
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
