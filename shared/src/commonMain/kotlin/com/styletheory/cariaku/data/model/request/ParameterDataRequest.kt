package com.styletheory.cariaku.data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ParameterDataRequest(
    val objectId: String? = null
)