package com.styletheory.cariaku.data.model.response

import com.styletheory.cariaku.data.model.Assistant
import kotlinx.serialization.Serializable

@Serializable
data class AssistantResponse(
    val results: List<Assistant>
)