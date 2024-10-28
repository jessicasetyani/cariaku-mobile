package com.styletheory.cariaku.android.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String) {
    @Serializable
    object Auth : Screen("auth")

    @Serializable
    object Home : Screen("home")

    @Serializable
    data class Chat(val assistantId: String = "") : Screen("chat/{assistantId}") {
        companion object {
            fun createRoute(assistantId: String): String {
                return "chat/$assistantId"
            }
        }
    }
}
