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

class OpenRouterClient(
    private val httpClient: HttpClient
) {

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
//                .call()

            return response.body<ChatCompletionResponse>()
//            return when(response.status.value) {
//                in 200..299 -> {
//                    val chatCompletionResponse = response.body<ChatCompletionResponse>()
//                    Result.Success(chatCompletionResponse)
//                }
//
//                401 -> Result.Error(NetworkError.UNAUTHORIZED)
//                409 -> Result.Error(NetworkError.CONFLICT)
//                408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
//                413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
//                429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
//                in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
//                else -> Result.Error(NetworkError.UNKNOWN)
//            }
//        } catch(e: UnresolvedAddressException) {
//            return Result.Error(NetworkError.NO_INTERNET)
//        } catch(e: SerializationException) {
//            return Result.Error(NetworkError.SERIALIZATION)
        } catch(e: Exception) {
            throw e
//            return e.toString()
            //return Result.Error(NetworkError.UNKNOWN)
        }
    }
}