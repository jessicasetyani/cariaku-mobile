package com.styletheory.cariaku.data.remote

import com.styletheory.cariaku.util.Constant.HELICONE_API_KEY
import com.styletheory.cariaku.util.Constant.OPENROUTER_API_KEY
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                }, contentType = ContentType.Any
            )
        }
        // Set default headers for all requests
        defaultRequest {
            header("Helicone-Auth", "Bearer ${HELICONE_API_KEY}")
            header("Authorization", "Bearer ${OPENROUTER_API_KEY}")
        }
//        install(Auth) {
//            bearer {
//                loadTokens { BearerTokens(apiKey, apiKey) }
//            }
//        }
    }
}