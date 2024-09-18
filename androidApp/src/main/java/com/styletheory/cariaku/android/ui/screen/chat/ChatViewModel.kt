package com.styletheory.cariaku.android.ui.screen.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.styletheory.cariaku.data.repository.ChatRepository
import com.styletheory.cariaku.data.model.ChatMessage
import com.styletheory.cariaku.util.Constant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChatViewModel(private val chatRepository: ChatRepository) : ViewModel() {
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages

    private val _inputMessage = MutableStateFlow("")
    val inputMessage: StateFlow<String> = _inputMessage

    fun sendMessage(message: String) {
        if(message.isNotBlank()) {
            val userMessage = ChatMessage(message, getCurrentTimestamp(), true)
            _chatMessages.value = _chatMessages.value + userMessage

            viewModelScope.launch {
                try {
                    val aiResponse = chatRepository.sendMessageToAI(message)
                    val aiMessage = ChatMessage(aiResponse, getCurrentTimestamp(), false)
                    _chatMessages.value = _chatMessages.value + aiMessage
                } catch(e: Exception) {
                    // Handle error
                }
            }

            _inputMessage.value = ""
        }
    }

    fun updateInputMessage(message: String) {
        _inputMessage.value = message
    }

    private fun getCurrentTimestamp(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.FORMAT_DATETIME_HH_MM_A))
    }
}
