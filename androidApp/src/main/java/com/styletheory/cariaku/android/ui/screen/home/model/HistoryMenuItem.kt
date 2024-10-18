package com.styletheory.cariaku.android.ui.screen.home.model

import java.time.LocalTime

data class HistoryMenuItem(
    val title: String,
    val summary: String,
    val lastAccess: LocalTime
)