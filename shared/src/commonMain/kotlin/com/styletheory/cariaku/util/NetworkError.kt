package com.styletheory.cariaku.util

enum class NetworkError : Error {
    REQUEST_TIMEOUT,
    UNAUTHORIZED,
    CONFLICT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    PAYLOAD_TOO_LARGE,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN;

    fun toUserFriendlyMessage(): String {
        return when (this) {
            REQUEST_TIMEOUT -> "Request timed out. Please try again."
            UNAUTHORIZED -> "Invalid username or password."
            CONFLICT -> "Conflict occurred. Please try again."
            TOO_MANY_REQUESTS -> "Too many requests. Please try again later."
            NO_INTERNET -> "No internet connection. Please check your network and try again."
            PAYLOAD_TOO_LARGE -> "Payload too large. Please try again."
            SERVER_ERROR -> "Server error. Please try again later."
            SERIALIZATION -> "Serialization error. Please try again."
            UNKNOWN -> "An unknown error occurred. Please try again."
        }
    }
}
