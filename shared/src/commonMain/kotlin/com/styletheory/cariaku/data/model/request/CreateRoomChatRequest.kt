package com.styletheory.cariaku.data.model.request

import com.styletheory.cariaku.data.model.TypeDate
import com.styletheory.cariaku.data.model.TypePointer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateRoomChatRequest(
    val title: String,
    val user: TypePointer,
    val assistant: TypePointer,
    @SerialName("last_message_at")
    val lastMessageAt: TypeDate,
    @SerialName("is_active")
    val isActive: Boolean
)