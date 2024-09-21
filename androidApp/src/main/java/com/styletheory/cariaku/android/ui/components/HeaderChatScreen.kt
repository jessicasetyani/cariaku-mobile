package com.styletheory.cariaku.android.ui.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.styletheory.cariaku.android.R
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderChatScreen(onNavigateBack: () -> Unit, title: String, isLoading: Boolean, modifier: Modifier = Modifier) {
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
                AnimatedVisibility(
                    visible = isLoading,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = "Thinking...",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                AnimatedVisibility(
                    visible = !isLoading,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = title,
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
