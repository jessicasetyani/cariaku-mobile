package com.styletheory.cariaku.data.remote

import com.styletheory.cariaku.data.model.request.ChatCompletionRequest
import com.styletheory.cariaku.data.model.response.ChatCompletionResponse
import com.styletheory.cariaku.data.remote.ApiRoute.CARI_AKU_BASE_URL
import com.styletheory.cariaku.util.Constant.BACK_FOR_APP_API_ID
import com.styletheory.cariaku.util.Constant.BACK_FOR_APP_REST_API_KEY
import com.styletheory.cariaku.util.Constant.HELICONE_API_KEY
import com.styletheory.cariaku.util.Constant.OPENROUTER_API_KEY
import com.styletheory.cariaku.util.Constant.YOUR_SITE_NAME
import io.ktor.client.HttpClient
import io.ktor.client.call.body
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
                urlString = ApiRoute.BASE_URL_OPEN_ROUTER + "/chat/completions"
            ) {
                contentType(ContentType.Application.Json)
                headers {
                    header("Helicone-Auth", "Bearer $HELICONE_API_KEY")
                    header("Authorization", "Bearer $OPENROUTER_API_KEY")
                    header("HTTP-Referer", CARI_AKU_BASE_URL)
                    header("X-Title", YOUR_SITE_NAME)
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
