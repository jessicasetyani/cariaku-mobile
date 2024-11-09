package com.styletheory.cariaku.android.ui.screen.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.styletheory.cariaku.android.util.DateFormatUtil
import com.styletheory.cariaku.data.local.DataStoreRepository
import com.styletheory.cariaku.data.model.Assistant
import com.styletheory.cariaku.data.model.ChatMessageOpenAi
import com.styletheory.cariaku.data.remote.BackForAppClient
import com.styletheory.cariaku.data.repository.ChatRepository
import com.styletheory.cariaku.util.Constant.INITIATE_FIRST_CHAT
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.Boolean

class ChatViewModel(
    private val chatRepository: ChatRepository,
    private val backForAppClient: BackForAppClient,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    private val _assistant = MutableStateFlow<Assistant?>(null)
    val assistant: StateFlow<Assistant?> = _assistant

    private val _assistantName = MutableStateFlow("")
    val assistantName: StateFlow<String> = _assistantName

    private val _chatMessages = MutableStateFlow<List<ChatMessageOpenAi>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessageOpenAi>> = _chatMessages

    private val _inputMessage = MutableStateFlow("")
    val inputMessage: StateFlow<String> = _inputMessage

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var isRoomCreated = false

    fun fetchAssistantDetails(assistantId: String) {
        viewModelScope.launch {
            try {
                val assistant = backForAppClient.getAssistantDetailById(assistantId)
                _assistant.value = assistant
                _assistantName.value = assistant?.name ?: "DAN"
                _isLoading.value = false
            } catch(e: Exception) {
                // Handle error
            }
        }
    }

    fun sendMessage(message: String) {
        if(message.isNotBlank()) {
            val userMessage = ChatMessageOpenAi(message, getCurrentTimestamp(), true)
            _chatMessages.value += userMessage
            _isLoading.value = true

            viewModelScope.launch {
                try {
                    //create ChatRoom with saving ChatClass to BackForApp DB
                    if (!isRoomCreated) {
                        createRoom(message)
                        isRoomCreated = true
                    }
                    val aiResponse = chatRepository.sendMessageToAI(message, assistant.value?.model)
                    Log.d("ChatViewModel", "API Response: $aiResponse")
                    if(aiResponse.isNotEmpty()) {
                        val aiMessage = ChatMessageOpenAi(aiResponse, getCurrentTimestamp(), false)
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

    fun createRoom(message: String) {
        viewModelScope.launch {
            val response = backForAppClient.createRoomChat(INITIATE_FIRST_CHAT)
        }
    }

    //save message to BackForApp DB
//    fun saveMessage(message: String) {
//        if(message.isNotBlank()) {
//            val userMessage = ChatMessageOpenAi(message, getCurrentTimestamp(), true)
//            _chatMessages.value += userMessage
//            viewModelScope.launch {
//                try {
//                    val response = backForAppClient.saveMessage(
//                        messageRequestData = MessageClass(
//                            content = message,
//                            isRead = false,
//                            totalTokens = 1,
//                            customPrompt = "",
//                            textContent = "",
//                            processedContent = "",
//                            chat = ChatClass
//                    )
//                    Log.d("ChatViewModel", "save DB Response: $response")
//                    if(response.isNotEmpty()) {
//                        val aiMessage = ChatMessageOpenAi(aiResponse, getCurrentTimestamp(), false)
//                        _chatMessages.value += aiMessage
//                    } else {
//                        Log.e("ChatViewModel", "Empty response from AI")
//                    }
//                } catch(e: Exception) {
//                    Log.e("ChatViewModel", "Error sending message: ${e.message}")
//                } finally {
//                    _isLoading.value = false
//                }
//
//            }
//
//            _inputMessage.value = ""
//        }
//    }
}
