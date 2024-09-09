package com.styletheory.cariaku.android

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search

@Composable
fun QuickAccessSection(assistants: List<String>) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = "Quick Access (Asisten Andalan)", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(assistants) { assistant ->
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { /* Start conversation with assistant */ }
                        .width(100.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_actual_assistant), // Replace with actual icon resource
                        contentDescription = null,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(bottom = 4.dp)
                    )
                    Text(
                        text = assistant,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomePage() {
    var searchQuery by remember { mutableStateOf("") }

    val onActiveChange: (Boolean) -> Unit = { /* Handle active change */ }
    val colors1 = SearchBarDefaults.colors()
    val topAssistants = listOf("Assistant 1", "Assistant 2", "Assistant 3", "Assistant 4")
    QuickAccessSection(assistants = topAssistants)
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { /* Handle search */ },
                expanded = false,
                onExpandedChange = { onActiveChange(it) },
                enabled = true,
                placeholder = { Text("Cari Apa Nih? CariAku") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = null,
                colors = colors1.inputFieldColors,
                interactionSource = null,
            )
        },
        expanded = false,
        onExpandedChange = onActiveChange,
        modifier = Modifier.fillMaxWidth(),
        shape = SearchBarDefaults.inputFieldShape,
        colors = colors1,
        tonalElevation = SearchBarDefaults.TonalElevation,
        shadowElevation = SearchBarDefaults.ShadowElevation,
        windowInsets = SearchBarDefaults.windowInsets,
        content = {
            // Search suggestions can be added here
        }
    )
}

@Preview(showBackground = true)
@Composable
fun WelcomePagePreview() {
    MaterialTheme {
        WelcomePage()
    }
}
