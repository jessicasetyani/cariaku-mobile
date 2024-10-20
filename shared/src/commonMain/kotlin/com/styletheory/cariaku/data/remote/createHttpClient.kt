package com.styletheory.cariaku.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerAuthConfig
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(engine: HttpClientEngine, apiKey: String): HttpClient {
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
            header("Helicone-Auth", "Bearer pk-helicone-56oieny-d7kuloa-r2jwiwq-mzb5rki")
            header("Authorization", "Bearer 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")
        }
//        install(Auth) {
//            bearer {
//                loadTokens { BearerTokens(apiKey, apiKey) }
//            }
//        }
    }
}