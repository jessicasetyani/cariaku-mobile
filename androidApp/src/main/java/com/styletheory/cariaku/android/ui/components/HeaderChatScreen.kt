package com.styletheory.cariaku.android.ui.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.styletheory.cariaku.android.R
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderChatScreen(onNavigateBack: () -> Unit, title: String, isLoading: Boolean, modifier: Modifier = Modifier) {
    var displayedText by remember { mutableStateOf(title) }
    var thinkingTextVisible by remember { mutableStateOf(false) }
    val thinkingText = "Thinking..."
    val scale by animateFloatAsState(
        targetValue = if (thinkingTextVisible) 1.2f else 1.0f,
        animationSpec = tween(durationMillis = 500, easing = LinearEasing),
        label = "ThinkingTextScale"
    )

    LaunchedEffect(isLoading) {
        if (isLoading) {
            thinkingTextVisible = true
            displayedText = thinkingText
        } else {
            thinkingTextVisible = false
            displayedText = title
        }
    }

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
                if(thinkingTextVisible) {
                    Text(
                        text = thinkingText,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .graphicsLayer(scaleX = scale, scaleY = scale)
                            .animateContentSize()
                    )
                } else {
                    Text(
                        text = displayedText,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Arrow Icon"
                )
            }
        },
        modifier = modifier
    )
}
