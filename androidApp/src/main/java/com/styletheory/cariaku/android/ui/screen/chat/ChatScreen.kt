package com.styletheory.cariaku.android.ui.screen.chat

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.styletheory.cariaku.android.ui.components.HeaderChatScreen
import com.styletheory.cariaku.android.ui.components.MessageBubble
import com.styletheory.cariaku.data.model.ChatMessage
import com.styletheory.cariaku.data.remote.OpenRouterClient
import com.styletheory.cariaku.data.remote.createHttpClient
import com.styletheory.cariaku.data.repository.ChatRepository
import com.styletheory.cariaku.util.Constant.API_KEY_OPEN_ROUTE
import io.ktor.client.engine.okhttp.OkHttp
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.styletheory.cariaku.util.Constant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreen(onNavigateBack: () -> Unit) {
    val client = remember {
        OpenRouterClient(createHttpClient(OkHttp.create(), API_KEY_OPEN_ROUTE))
    }
    val chatRepository = remember { ChatRepository(client) }
    val chatViewModel: ChatViewModel = viewModel(factory = ChatViewModelFactory(chatRepository))

    val chatMessages by chatViewModel.chatMessages.collectAsState()
    val inputMessage by chatViewModel.inputMessage.collectAsState()
    val isLoading by chatViewModel.isLoading.collectAsState()

    // Mutable state to manage the title
    val title = remember { mutableStateOf("Assistant Name") }

    // Update title based on loading state
    if (isLoading) {
        title.value = "Thinking..."
    } else {
        title.value = "Assistant Name"
    }

    Scaffold(
        topBar = {
            HeaderChatScreen(onNavigateBack = onNavigateBack, title = title.value)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
            ) {
                chatViewModel.saveAssistant()
                ChatMessages(
                    chatMessages = chatMessages,
                    isLoading = isLoading,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                ChatInput(
                    message = inputMessage,
                    onMessageChange = chatViewModel::updateInputMessage,
                    onSend = { chatViewModel.sendMessage(inputMessage) }
                )
            }
        }
    )
}

@Composable
fun ChatMessages(
    chatMessages: List<ChatMessage>,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .fillMaxHeight(),
        reverseLayout = true,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(chatMessages.reversed()) { message ->
            MessageBubble(chatMessage = message)
        }
        // Remove the "Thinking..." message logic
        // if (isLoading) {
        //     item {
        //         val lastUserMessageIndex = chatMessages.indexOfLast { it.isUser }
        //         if (lastUserMessageIndex != -1) {
        //             MessageBubble(chatMessage = ChatMessage("Thinking...", getCurrentTimestamp(), false))
        //         }
        //     }
        // }
    }
}

fun getCurrentTimestamp(): String {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.FORMAT_DATETIME_HH_MM_A))
}


@Composable
fun ChatInput(
    message: String,
    onMessageChange: (String) -> Unit,
    onSend: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = message,
            onValueChange = onMessageChange,
            modifier = Modifier
                .weight(1f),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions(onSend = {
                onSend()
                keyboardController?.hide() // Hide the keyboard
            }),
            placeholder = { Text("Type a message") }
        )
        IconButton(onClick = {
            onSend()
            keyboardController?.hide() // Hide the keyboard
        }) {
            Icon(Icons.Default.Send, contentDescription = "Send")
        }
    }
}
