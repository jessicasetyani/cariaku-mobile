package com.styletheory.cariaku.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CreateObjectResponse(
    val objectId: String,
    val createdAt: String
)