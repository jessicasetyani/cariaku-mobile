package com.styletheory.cariaku.android.ui.screen.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.styletheory.cariaku.data.local.DataStoreRepository
import com.styletheory.cariaku.data.remote.BackForAppClient
import com.styletheory.cariaku.data.repository.ChatRepository

class ChatViewModelFactory(
    private val chatRepository: ChatRepository,
    private val backForAppClient: BackForAppClient,
    private val dataStoreRepository: DataStoreRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChatViewModel(chatRepository, backForAppClient, dataStoreRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
