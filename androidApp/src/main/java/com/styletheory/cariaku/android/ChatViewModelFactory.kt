package com.styletheory.cariaku.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.styletheory.cariaku.android.chat.ChatViewModel
import com.styletheory.cariaku.data.repository.ChatRepository

class ChatViewModelFactory(private val chatRepository: ChatRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChatViewModel(chatRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
