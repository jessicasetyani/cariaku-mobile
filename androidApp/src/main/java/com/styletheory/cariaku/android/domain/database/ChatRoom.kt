package com.styletheory.cariaku.android.domain.database

import com.parse.ParseObject
import com.parse.ParseUser

data class ChatRoom(
    val objectId: String,
    val createdAt: Long,
    val updatedAt: Long,
    val title: String,
    val user: ParseUser,
    val assistant: ParseObject
)