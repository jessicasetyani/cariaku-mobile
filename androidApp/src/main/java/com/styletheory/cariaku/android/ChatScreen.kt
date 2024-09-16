package com.styletheory.cariaku.android

import android.annotation.SuppressLint
import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.variable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.styletheory.cariaku.Greeting
import com.styletheory.cariaku.network.OpenRouterClient
import com.styletheory.cariaku.network.model.Message
import com.styletheory.cariaku.network.model.request.ChatCompletionRequest
import com.styletheory.cariaku.util.Constant
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * The main Composable function that sets up the chat interface.
 *
 * This function initializes the state for the user's message and the list of chat messages.
 * It arranges the chat interface with a header, content screen, and footer screen.
 * It handles the sending of new messages and updates the message list accordingly.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreen(client: OpenRouterClient) {
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf("What is the meaning of life?") }
    var chatMessage by remember { mutableStateOf("") }
    val chatMessages = remember {
        mutableStateOf(
            if (chatMessage.isEmpty()) listOf() else listOf(
                ChatMessage(chatMessage, LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.FORMAT_DATETIME_HH_MM_A)), true)
            )
        )
    }

    LaunchedEffect(true) {
        scope.launch {
            try {
                val chatRequest = ChatCompletionRequest(
                    model = Constant.MODEL_AI_REFLECTION,
                    messages = listOf(
                        Message(
                            role = Constant.ROLE_SYSTEM,
                            content = "You are a knowledgeable and friendly assistant that provides clear and concise answers."
                        ),
                        Message(
                            role = Constant.ROLE_USER,
                            content = text
                        )
                    )
                )
                val response = Greeting().chatWithAI(chatRequest, client)
                val choice = response.choices.first()
                val message = choice.message
                val responseMessage = ChatMessage(
                    message.content,
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.FORMAT_DATETIME_HH_MM_A)),
                    false
                )
                chatMessages.value = chatMessages.value + responseMessage
            } catch (e: Exception) {
                e.localizedMessage?: "error"
            }
        }
    }

    Header(onNavigateBack = { /* Handle navigation back here */ })
    Column(
        modifier = Modifier
           .fillMaxSize()
           .background(Color.White)
    ) {
        Box(
            modifier = Modifier
               .weight(1f)
               .fillMaxWidth()
        ) {
            ContentScreen(chatMessages = chatMessages.value.reversed())
        }
        FooterScreen(chatMessage, onMessageChange = { chatMessage = it }, onSend = {
            val newChatMessage =
                ChatMessage(chatMessage, LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.FORMAT_DATETIME_HH_MM_A)), true)
            chatMessages.value = chatMessages.value + newChatMessage
            chatMessage = ""
        })
    }
}

//... rest of the file remains the same...
