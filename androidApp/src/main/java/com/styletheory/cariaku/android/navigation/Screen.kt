package com.styletheory.cariaku.android.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String) {
    @Serializable
    object Auth : Screen("auth")
    @Serializable
    object Home : Screen("home")
    @Serializable
    object Chat : Screen("chat/{assistantId}")
}