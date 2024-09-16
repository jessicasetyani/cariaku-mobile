package com.styletheory.cariaku.android

import android.annotation.SuppressLint
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
import com.styletheory.cariaku.Greeting
import com.styletheory.cariaku.network.OpenRouterClient
import com.styletheory.cariaku.network.model.Message
import com.styletheory.cariaku.network.model.request.ChatCompletionRequest
import com.styletheory.cariaku.util.Constant
import com.styletheory.cariaku.util.Constant.RENEW_TOKEN_QUOTA
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

    var chatMessage by remember { mutableStateOf("") }
    val chatRequestMessages = remember {
        mutableStateOf(
            listOf(
                Message(
                    role = Constant.ROLE_SYSTEM,
                    content = Constant.SYSTEM_PROMPT_INITIAL
                )
            )
        )
    }
    val chatRequestMessagesSummaries = remember {
        mutableStateOf(
            listOf(
                Message(
                    role = Constant.ROLE_SYSTEM,
                    content = Constant.SYSTEM_PROMPT_SUMMARY
                )
            )
        )
    }
    val chatMessages = remember {
        mutableStateOf(
            if(chatMessage.isEmpty()) listOf() else listOf(
                ChatMessage(chatMessage, LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.FORMAT_DATETIME_HH_MM_A)), true)
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(onNavigateBack = { /* Handle navigation back here */ })
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            ContentScreen(chatMessages = chatMessages.value.reversed())
        }
        FooterScreen(chatMessage, onMessageChange = { chatMessage = it }, onSend = {
            //show Bubble Message from User
            chatMessages.value += ChatMessage(
                chatMessage,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.FORMAT_DATETIME_HH_MM_A)),
                true
            )
            //show Bubble Message from AI
            scope.launch {
                try {
                    chatRequestMessages.value += listOf(
                        Message(
                            role = Constant.ROLE_USER,
                            content = chatMessage
                        )
                    )
                    val chatRequest = ChatCompletionRequest(
                        model = Constant.MODEL_AI_RELECTION,
                        messages = chatRequestMessages.value
                    )
                    val response = Greeting().chatWithAI(chatRequest, client)
                    val usage = response.usage
                    val choice = response.choices.first()
                    val message = choice.message
                    val responseMessage = ChatMessage(
                        message.content + "\nTotal Token: " + usage.totalTokens.toString(),
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constant.FORMAT_DATETIME_HH_MM_A)),
                        false
                    )
                    chatMessages.value += responseMessage
                    chatRequestMessages.value += listOf(
                        Message(
                            role = Constant.ROLE_ASSISTANT,
                            content = message.content
                        )
                    )

                    if(usage.totalTokens >= RENEW_TOKEN_QUOTA) {
                        chatRequestMessages.value.filter { it.role != Constant.ROLE_SYSTEM }
                            .also { filteredMessages ->
                                var newChatRequestWithSummaries: String = ""
                                newChatRequestWithSummaries =
                                    filteredMessages
                                        .joinToString("\n") { "\n" + it.role + ": " + it.content }
                                val chatSummaries = Constant.USER_PROMPT_SUMMARY + newChatRequestWithSummaries
                                chatRequestMessagesSummaries.value += listOf(
                                    Message(
                                        role = Constant.ROLE_USER,
                                        content = chatSummaries
                                    )
                                )
                                val chatRequestSummaries = ChatCompletionRequest(
                                    model = Constant.MODEL_AI_GEMINI,
                                    messages = chatRequestMessagesSummaries.value
                                )
                                val newResponse = Greeting().chatWithAI(chatRequestSummaries, client)
                                // reset chatRequestMessages content list to have only 2 content, from System and 1 from summaries response
                                chatRequestMessages.value = listOf(chatRequestMessages.value.first())
                                chatRequestMessages.value += listOf(
                                    Message(
                                        role = Constant.ROLE_ASSISTANT,
                                        content = newResponse.choices.first().message.content
                                    )
                                )
                                //reset value of chatRequestMessagesSummaries to be only initialize by system
                                chatRequestMessagesSummaries.value = listOf(chatRequestMessagesSummaries.value.first())

                            }
                    }

                } catch(e: Exception) {
                    e.localizedMessage ?: "error"
                }
            }
            // Reset chat message and request messages
            scope.launch {
                chatMessage = ""
//                chatRequestMessages.value = listOf()
            }
        })
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
                .weight(0.75f)
                .fillMaxWidth()
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
