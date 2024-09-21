package com.styletheory.cariaku.android.ui.screen.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.util.Log
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.livequery.SubscriptionHandling
import com.styletheory.cariaku.android.util.AssistantTable
import com.styletheory.cariaku.data.model.ChatMessage
import com.styletheory.cariaku.data.repository.ChatRepository
import com.styletheory.cariaku.util.Constant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class ChatViewModel(private val chatRepository: ChatRepository) : ViewModel() {
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages

    private val _inputMessage = MutableStateFlow("")
    val inputMessage: StateFlow<String> = _inputMessage

    private var subscriptionTableChat: SubscriptionHandling<ParseObject>? = null
    private var subscriptionTableMessage: SubscriptionHandling<ParseObject>? = null

    fun saveAssistant() {
        val getAssistantQuery = ParseQuery.getQuery<ParseObject>(AssistantTable.NAME)
        getAssistantQuery.whereContains(AssistantTable.ASSISTANT_NAME, Constant.MODEL_AI_REFLECTION)
        getAssistantQuery.findInBackground { assistants, messageError ->
            if (messageError == null) {
                if (assistants.isEmpty()) {
                    saveAssistantToDb()
                }
            } else {
                Log.e("ChatViewModel", "Error fetching assistant: ${messageError.message}")
            }
        }
    }

    fun saveAssistantToDb() {
        val assistant = ParseObject(AssistantTable.NAME)
        val localDateTime = LocalDateTime.now()
        val epochMillis = localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli()

        assistant.put(AssistantTable.ASSISTANT_NAME, Constant.MODEL_AI_REFLECTION)
        assistant.put(AssistantTable.IS_ACTIVE, true)
        assistant.put(AssistantTable.DESCRIPTION, Constant.SYSTEM_PROMPT_INITIAL)

        assistant.saveInBackground { messageError ->
            if (messageError == null) {
                Log.i("ChatViewModel", "Assistant saved successfully")
            } else {
                Log.e("ChatViewModel", "Error saving assistant: ${messageError.message}")
            }
        }
    }

    fun sendMessage(message: String) {
        if (message.isNotBlank()) {
            val userMessage = ChatMessage(message, getCurrentTimestamp(), true)
            _chatMessages.value += userMessage

            viewModelScope.launch {
                try {
                    val aiResponse = chatRepository.sendMessageToAI(message)
                    val aiMessage = ChatMessage(aiResponse, getCurrentTimestamp(), false)
                    _chatMessages.value += aiMessage
                } catch (e: Exception) {
                    Log.e("ChatViewModel", "Error sending message: ${e.message}")
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
