package com.styletheory.cariaku.data.model.response

import com.styletheory.cariaku.data.model.Assistant
import kotlinx.serialization.Serializable

@Serializable
data class AllAssistantsResponse(
    val results: List<Assistant>
)