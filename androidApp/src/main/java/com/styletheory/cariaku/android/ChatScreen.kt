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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
                //  val response = Greeting().chatWithAI(chatRequest, client)

            } catch(e: Exception) {
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

fun removeInitialMessage(message: ChatMessage): List<ChatMessage> {
    return if(message.text.isNotEmpty()) {
        mutableListOf()
    } else {
        listOf(message)
    }
}

/**
 * A Composable function that displays the chat header.
 *
 * This function displays an icon and the assistant's name.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(onNavigateBack: () -> Unit) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_placeholder_assistant),
                    contentDescription = "Assistant Icon",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Assistant Name",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(Icons.Default.ArrowBackIosNew, contentDescription = "Back")
            }
        }
    )
}

/**
 * A Composable function that displays the chat messages.
 *
 * @param chatMessages A list of [ChatMessage] objects to be displayed.
 *
 * This function displays the list of chat messages in a [LazyColumn].
 */
@Composable
fun ContentScreen(chatMessages: List<ChatMessage>) {
    Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(Color.LightGray.copy(alpha = 0.2f)) // Subtle watermark background
    ) {
        Box(
            modifier = Modifier
              .fillMaxWidth()
              .weight(1f)
              .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LazyColumn(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 16.dp),
                reverseLayout = true
            ) {
                items(chatMessages) { message ->
                    MessageBubble(message)
                }
            }
        }
    }
}

/**
 * A Composable function that displays an individual chat message.
 *
 * @param chatMessage A [ChatMessage] object to be displayed.
 *
 * This function displays the message text and timestamp, with different styling based on whether the message is from the user or the assistant.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MessageBubble(chatMessage: ChatMessage) {
    val alignment = if(chatMessage.isUser) Alignment.End else Alignment.Start
    val backgroundColor =
        if(chatMessage.isUser) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onTertiaryContainer
    val textColor = Color.White

    Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 8.dp),
        horizontalAlignment = alignment
    ) {
        Box(
            modifier = Modifier
              .background(backgroundColor, shape = RoundedCornerShape(16.dp))
              .padding(12.dp)
        ) {
            Text(
                text = chatMessage.text,
                color = textColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Text(
            text = chatMessage.timestamp,
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )
    }
}

/**
 * A data class that represents a chat message.
 *
 * @property text The text content of the message.
 * @property timestamp The timestamp when the message was sent.
 * @property isUser A boolean indicating whether the message is from the user.
 */
data class ChatMessage(
    val text: String,
    val timestamp: String,
    val isUser: Boolean
)

/**
 * A Composable function that displays the input field and send button.
 *
 * @param message The current text in the input field.
 * @param onMessageChange A callback function to handle changes to the input field.
 * @param onSend A callback function to handle the sending of the message.
 *
 * This function provides an input field for the user to type messages and a send button to send the message.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FooterScreen(message: String, onMessageChange: (String) -> Unit, onSend: () -> Unit) {
    Row(
        modifier = Modifier
          .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = message,
            onValueChange = onMessageChange,
            modifier = Modifier
              .weight(1f),
            placeholder = { Text("Type a message") },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = onSend) {
                    Icon(Icons.Default.Send, contentDescription = "Send")
                }
            }
        )
    }
}

/**
 * A function that reads chat messages from a resource file.
 *
 * This function reads messages from a raw resource file and returns them as a list of [ChatMessage] objects.
 *
 * @return A list of [ChatMessage] objects.
 */
fun readMessagesFromResource(): List<ChatMessage> {
    val chatMessages = mutableListOf<ChatMessage>()
    try {
        val inputStream = Resources.getSystem().openRawResource(R.raw.messages)
        val reader = inputStream.bufferedReader()
        while(reader.ready()) {
            val text = reader.readLine()
            val timestamp = reader.readLine()
            val isUser = reader.readLine().toBoolean()
            chatMessages.add(ChatMessage(text, timestamp, isUser))
        }
        reader.close()
    } catch(e: Exception) {
        Log.e("ChatScreen", "Error reading messages", e)
    }
    return chatMessages
}
