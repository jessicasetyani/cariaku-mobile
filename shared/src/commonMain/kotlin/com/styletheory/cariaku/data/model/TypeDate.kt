package com.styletheory.cariaku.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeDate(
    @Serializable(with = DateSerializer::class)
    @SerialName("__type")
    val type: String = "Date",
    val iso: String
)