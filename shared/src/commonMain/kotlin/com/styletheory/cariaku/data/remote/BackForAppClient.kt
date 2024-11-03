package com.styletheory.cariaku.data.remote

import com.styletheory.cariaku.data.model.Assistant
import com.styletheory.cariaku.data.model.UserProfile
import com.styletheory.cariaku.data.model.UserProfileResponse
import com.styletheory.cariaku.data.model.request.LoginUserRequest
import com.styletheory.cariaku.data.model.request.ParameterDataRequest
import com.styletheory.cariaku.data.model.response.AllAssistantsResponse
import com.styletheory.cariaku.data.model.response.UserResponse
import com.styletheory.cariaku.util.Constant.BACK_FOR_APP_API_ID
import com.styletheory.cariaku.util.Constant.BACK_FOR_APP_REST_API_KEY
import com.styletheory.cariaku.util.Constant.X_PARSE_APPLICATION_ID_HEADER
import com.styletheory.cariaku.util.Constant.X_PARSE_REST_API_KEY_HEADER
import com.styletheory.cariaku.util.NetworkError
import com.styletheory.cariaku.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class BackForAppClient(private val httpClient: HttpClient) {

    suspend fun loginUser(registerUserRequest: LoginUserRequest): Result<UserResponse, NetworkError> {
        val response = try {
            httpClient.post(
                urlString = ApiRoute.BASE_URL_BACK_4_APP + "/login"
            ) {
                method = HttpMethod.Post
                contentType(ContentType.Application.Json)
                header("X-Parse-Application-Id", "${BACK_FOR_APP_API_ID}")
                header("X-Parse-REST-API-Key", "${BACK_FOR_APP_REST_API_KEY}")
                header("X-Parse-Revocable-Session", 1)
                parameter("username", registerUserRequest.username)
                parameter("password", registerUserRequest.password)
            }
        } catch(e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch(e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in 200..299 -> {
                val responseBody = response.bodyAsText()
                val userResponse = Json { ignoreUnknownKeys = true }.decodeFromString<UserResponse>(responseBody)
                Result.Success(userResponse)
            }

            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun getCurrentUser(sessionToken: String): UserResponse {
        try {
            val response = httpClient.get(
                urlString = ApiRoute.BASE_URL_BACK_4_APP + "/users/me"
            ) {
                contentType(ContentType.Application.Json)
                headers {
                    contentType(ContentType.Application.Json)
                    header("X-Parse-Application-Id", "${BACK_FOR_APP_API_ID}")
                    header("X-Parse-REST-API-Key", "${BACK_FOR_APP_REST_API_KEY}")
                    header("X-Parse-Session-Token", sessionToken)
                }
            }

            return response.body<UserResponse>()

        } catch(e: Exception) {
            throw e
        }
    }

    suspend fun getUserProfile(requestData: ParameterDataRequest): UserProfile? {
        try {
            val whereJson = Json { encodeDefaults = true }.encodeToString(
                mapOf("objectId" to requestData.objectId)
            )
            val response = httpClient.get(
                urlString = ApiRoute.BASE_URL_BACK_4_APP + ApiRoute.CLASSES_PATH_NAME + "/UserProfile"
            ) {
                contentType(ContentType.Application.Json)
                headers {
                    contentType(ContentType.Application.Json)
                    header(X_PARSE_APPLICATION_ID_HEADER, BACK_FOR_APP_API_ID)
                    header(X_PARSE_REST_API_KEY_HEADER, BACK_FOR_APP_REST_API_KEY)
                }
                parameter("where", whereJson)
            }
            val responseBody = response.bodyAsText()
            println("API Response: $responseBody") // Log the response body

            val userProfileResponse = Json { ignoreUnknownKeys = true }.decodeFromString<UserProfileResponse>(responseBody)
            return userProfileResponse.results.firstOrNull()
        } catch(e: Exception) {
            throw e
        }
    }

    suspend fun getTopFavoriteAssistants(): AllAssistantsResponse {
        try {
            val response = httpClient.get(
                urlString = ApiRoute.BASE_URL_BACK_4_APP + ApiRoute.CLASSES_PATH_NAME + "/Assistant"
            ) {
                contentType(ContentType.Application.Json)
                headers {
                    contentType(ContentType.Application.Json)
                    header(X_PARSE_APPLICATION_ID_HEADER, BACK_FOR_APP_API_ID)
                    header(X_PARSE_REST_API_KEY_HEADER, BACK_FOR_APP_REST_API_KEY)
                }
            }
            val responseBody = response.bodyAsText()
            println("API Response: $responseBody") // Log the response body

            return response.body<AllAssistantsResponse>()

        } catch(e: Exception) {
            throw e
        }
    }

    suspend fun getAssistantDetailById(assistantId: String): Assistant? {
        try {
            val whereJson = Json { encodeDefaults = true }.encodeToString(
                mapOf("objectId" to assistantId)
            )
            val response = httpClient.get(
                urlString = ApiRoute.BASE_URL_BACK_4_APP + ApiRoute.CLASSES_PATH_NAME + "/Assistant"
            ) {
                contentType(ContentType.Application.Json)
                headers {
                    contentType(ContentType.Application.Json)
                    header(X_PARSE_APPLICATION_ID_HEADER, BACK_FOR_APP_API_ID)
                    header(X_PARSE_REST_API_KEY_HEADER, BACK_FOR_APP_REST_API_KEY)
                }
                parameter("where", whereJson)
            }
            val responseBody = response.bodyAsText()
            println("API Response: $responseBody") // Log the response body

            val assistantsResponse = Json { ignoreUnknownKeys = true }.decodeFromString<AllAssistantsResponse>(responseBody)
            return assistantsResponse.results.firstOrNull()


        } catch(e: Exception) {
            throw e
        }
    }



}