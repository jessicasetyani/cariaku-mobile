package com.styletheory.cariaku.android.domain

import com.parse.ParseObject

data class MessageModel(
    val objectId: String,
    val senderType: String,
    val isRead: Boolean,
    val updatedAt: Long,
    val totalToken: Int,
    val timestamp: Long,
    val chat: ParseObject,
    val content: String,
    val createdAt: Long
)
