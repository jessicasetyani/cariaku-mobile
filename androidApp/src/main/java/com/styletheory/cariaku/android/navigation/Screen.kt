package com.styletheory.cariaku.android.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Auth: Screen()
    @Serializable
    data object Home : Screen()
    @Serializable
    data object Chat : Screen()
}