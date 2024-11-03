package com.styletheory.cariaku.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypePointer(
    @Serializable(with = PointerSerializer::class)
    @SerialName("__type")
    val type: String = "Pointer",
    val className: String,
    val objectId: String
)