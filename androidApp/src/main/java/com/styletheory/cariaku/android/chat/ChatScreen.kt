package com.styletheory.cariaku.android.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isShiftPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.styletheory.cariaku.data.model.ChatMessage
import com.styletheory.cariaku.android.chat.components.HeaderChatScreen
import com.styletheory.cariaku.android.chat.components.MessageBubble


@Composable
fun ChatScreen(viewModel: ChatViewModel, onNavigateBack: () -> Unit) {
    val chatMessages by viewModel.chatMessages.collectAsState()
    val inputMessage by viewModel.inputMessage.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HeaderChatScreen(onNavigateBack = onNavigateBack)
        ChatMessages(
            chatMessages = chatMessages,
            modifier = Modifier.weight(1f)
        )
        ChatInput(
            message = inputMessage,
            onMessageChange = viewModel::updateInputMessage,
            onSend = { viewModel.sendMessage(inputMessage) }
        )
    }
}

@Composable
fun ChatMessages(
    chatMessages: List<ChatMessage>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        reverseLayout = true,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(chatMessages.reversed()) { message ->
            MessageBubble(message)
        }
    }
}


@Composable
fun ChatInput(
    message: String,
    onMessageChange: (String) -> Unit,
    onSend: () -> Unit
) {
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
                .weight(1f)
                .onKeyEvent {
                    if(it.key == Key.Enter && !it.isShiftPressed) {
                        onSend()
                        true
                    } else {
                        false
                    }
                },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions(onSend = { onSend() }),
            placeholder = { Text("Type a message") }
        )
        IconButton(onClick = onSend) {
            Icon(Icons.Default.Send, contentDescription = "Send")
        }
    }
}