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
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatScreen() {
    var message by remember { mutableStateOf("") }
    val messages = remember {
        mutableStateOf(
            // readMessagesFromResource()
            listOf(
                Message("Hello, how can I help you?", "12:00 PM", false),
                Message("I need some information.", "12:01 PM", true)
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header()
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            ContentScreen(messages = messages.value)
        }
        FooterScreen(message, onMessageChange = { message = it }, onSend = {
            val newMessage = Message(message, "12:02 PM", true)
            messages.value = messages.value + newMessage
            message = ""
        })
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
}

@Composable
fun ContentScreen(messages: List<Message>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.2f)) // Subtle watermark background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            items(messages) { message ->
                MessageBubble(message)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MessageBubble(message: Message) {
    val alignment = if(message.isUser) Alignment.End else Alignment.Start
    val backgroundColor = if(message.isUser) Color.Blue else Color.Gray
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
                text = message.text,
                color = textColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Text(
            text = message.timestamp,
            color = Color.Gray,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )
    }
}

data class Message(
    val text: String,
    val timestamp: String,
    val isUser: Boolean
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FooterScreen(message: String, onMessageChange: (String) -> Unit, onSend: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = message,
            onValueChange = onMessageChange,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
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

fun readMessagesFromResource(): List<Message> {
    val messages = mutableListOf<Message>()
    try {
        val inputStream = Resources.getSystem().openRawResource(R.raw.messages)
        val reader = inputStream.bufferedReader()
        while(reader.ready()) {
            val text = reader.readLine()
            val timestamp = reader.readLine()
            val isUser = reader.readLine().toBoolean()
            messages.add(Message(text, timestamp, isUser))
        }
        reader.close()
    } catch(e: Exception) {
        Log.e("ChatScreen", "Error reading messages", e)
    }
    return messages
}
