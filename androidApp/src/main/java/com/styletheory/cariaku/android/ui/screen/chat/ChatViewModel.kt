package com.styletheory.cariaku.android.ui.screen.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.styletheory.cariaku.android.util.DateFormatUtil
import com.styletheory.cariaku.data.model.ChatMessage
import com.styletheory.cariaku.data.remote.BackForAppClient
import com.styletheory.cariaku.data.repository.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChatViewModel(
    private val chatRepository: ChatRepository,
    private val backForAppClient: BackForAppClient
) : ViewModel() {
    private val _assistantName = MutableStateFlow("")
    val assistantName: StateFlow<String> = _assistantName

    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages

    private val _inputMessage = MutableStateFlow("")
    val inputMessage: StateFlow<String> = _inputMessage

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun fetchAssistantDetails(assistantId: String) {
        viewModelScope.launch {
            try {
                val assistant = backForAppClient.getAssistantDetailById(assistantId)
                _assistantName.value = assistant?.name ?: "DAN"
                _isLoading.value = false
            } catch(e: Exception) {
                // Handle error
            }
        }
    }

    fun sendMessage(message: String) {
        if(message.isNotBlank()) {
            val userMessage = ChatMessage(message, getCurrentTimestamp(), true)
            _chatMessages.value += userMessage

            _isLoading.value = true

            viewModelScope.launch {
                try {
                    val aiResponse = chatRepository.sendMessageToAI(message)
                    Log.d("ChatViewModel", "API Response: $aiResponse")
                    if(aiResponse.isNotEmpty()) {
                        val aiMessage = ChatMessage(aiResponse, getCurrentTimestamp(), false)
                        _chatMessages.value += aiMessage
                    } else {
                        Log.e("ChatViewModel", "Empty response from AI")
                    }
                } catch(e: Exception) {
                    Log.e("ChatViewModel", "Error sending message: ${e.message}")
                } finally {
                    _isLoading.value = false
                }

            }

            _inputMessage.value = ""
        }
    }

    fun updateInputMessage(message: String) {
        _inputMessage.value = message
    }

    private fun getCurrentTimestamp(): String {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateFormatUtil.FORMAT_DATETIME_HH_MM_A))
    }
}
