package com.styletheory.cariaku.data.repository

import com.styletheory.cariaku.data.remote.OpenRouterClient
import com.styletheory.cariaku.data.model.Message
import com.styletheory.cariaku.data.model.request.ChatCompletionRequest
import com.styletheory.cariaku.util.Constant

class ChatRepository(private val openRouterClient: OpenRouterClient) {
    private val chatRequestMessages = mutableListOf(
        Message(
            role = Constant.ROLE_SYSTEM,
            content = Constant.SYSTEM_PROMPT_INITIAL
        )
    )

    suspend fun sendMessageToAI(userMessage: String): String {
        chatRequestMessages.add(Message(role = Constant.ROLE_USER, content = userMessage))

        val chatRequest = ChatCompletionRequest(
            model = Constant.MODEL_AI_REFLECTION,
            messages = chatRequestMessages
        )

        val response = openRouterClient.chatCompletion(chatRequest)
        val aiMessage = response.choices.first().message.content + "\nTotal token: " +response.usage.totalTokens

        chatRequestMessages.add(Message(role = Constant.ROLE_ASSISTANT, content = aiMessage))

        if(response.usage.totalTokens >= Constant.RENEW_TOKEN_QUOTA) {
            summarizeConversation()
        }

        return aiMessage
    }

    private suspend fun summarizeConversation() {
        val filteredMessages = chatRequestMessages.filter { it.role != Constant.ROLE_SYSTEM }
        val conversationSummary = filteredMessages.joinToString("\n") { "${it.role}: ${it.content}" }

        val summaryRequest = ChatCompletionRequest(
            model = Constant.MODEL_AI_GEMINI,
            messages = listOf(
                Message(role = Constant.ROLE_SYSTEM, content = Constant.SYSTEM_PROMPT_SUMMARY),
                Message(role = Constant.ROLE_USER, content = Constant.USER_PROMPT_SUMMARY + conversationSummary)
            )
        )

        val summaryResponse = openRouterClient.chatCompletion(summaryRequest)
        val summary = summaryResponse.choices.first().message.content

        chatRequestMessages.clear()
        chatRequestMessages.add(Message(role = Constant.ROLE_SYSTEM, content = Constant.SYSTEM_PROMPT_INITIAL))
        chatRequestMessages.add(Message(role = Constant.ROLE_ASSISTANT, content = summary))
    }
}
