package com.styletheory.cariaku.android.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen() {
    @Serializable
    data object Auth : Screen()

    @Serializable
    data object Home : Screen()

    @Serializable
    data class Chat(val id: String) : Screen()
//    @Serializable
//    data class Chat(val assistantId: String = "") : Screen("chat/{assistantId}") {
//        companion object {
//            fun createRoute(assistantId: String): String {
//                return "chat/$assistantId"
//            }
//        }
//    }
}
