package com.styletheory.cariaku.android.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.styletheory.cariaku.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderChatScreen(
    onNavigateBack: () -> Unit,
    title: String,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    var displayedText by remember { mutableStateOf(title) }
    var thinkingTextVisible by remember { mutableStateOf(false) }
    val thinkingText = "Thinking..."
    val scale by animateFloatAsState(
        targetValue = if(thinkingTextVisible) 1.2f else 1.0f,
        animationSpec = tween(durationMillis = 500, easing = LinearEasing),
        label = "ThinkingTextScale"
    )

    LaunchedEffect(isLoading) {
        thinkingTextVisible = if(isLoading) {
            true
        } else {
            false
        }
    }

    TopAppBar(title = {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_placeholder_assistant),
                contentDescription = "Assistant Icon",
                modifier = Modifier.size(48.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier.padding(start = 8.dp), text = displayedText, style = MaterialTheme.typography.headlineSmall
                )
                if(thinkingTextVisible) {
                    Text(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .graphicsLayer(scaleX = scale, scaleY = scale)
                            .animateContentSize(),
                        text = thinkingText,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }, navigationIcon = {
        IconButton(onClick = onNavigateBack) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = "Back Arrow Icon"
            )
        }
    }, modifier = modifier
    )
}
