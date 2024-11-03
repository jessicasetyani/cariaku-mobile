package com.styletheory.cariaku.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypeRelation(
    @Serializable(with = RelationSerializer::class)
    @SerialName("__type")
    val type: String = "Relation",
    val className: String
)