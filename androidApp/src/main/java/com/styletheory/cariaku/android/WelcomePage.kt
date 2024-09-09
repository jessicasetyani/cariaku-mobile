package com.styletheory.cariaku.android

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search

@Composable
fun QuickAccessSection(assistants: List<String>) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = "Cari Asisten Favorit Kamu", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(assistants) { assistant ->
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable(onClick = { /* Start conversation with assistant */ })
                        .width(IntrinsicSize.Min)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .padding(bottom = 4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_placeholder_assistant), // Replace with assistant image
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(MaterialTheme.shapes.small)
                                .padding(end = 8.dp)
                        )
                        Text(
                            text = assistant,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TopicSuggestionsSection(topics: List<String>) {
    val listState = rememberLazyListState()
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = "CariAku Apa Hari Ini?", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(topics) { topic ->
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable(onClick = { /* Start conversation with topic suggestion */ })
                        .width(IntrinsicSize.Min)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .padding(bottom = 4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_placeholder_assistant), // Replace with CariAku logo/mascot
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(MaterialTheme.shapes.small)
                                .padding(end = 8.dp)
                        )
                        Text(
                            text = topic,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomePage() {
    var searchQuery by remember { mutableStateOf("") }

    val topAssistants = listOf("Assistant 1", "Assistant 2", "Assistant 3", "Assistant 4")
    val topicSuggestions = listOf(
        "Cara hemat uang jajan? CariAku tau nih!",
        "Mau tau rekomendasi film seru buat ditonton?",
        "Pengen dapet inspirasi buat dekorasi kamar?"
    )
    val onActiveChange: (Boolean) -> Unit = { /* Handle active change */ }
    val colors1 = SearchBarDefaults.colors()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Malam, [nama user]! Masih semangat nih? CariAku siap bantu kamu 24/7 loh!",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        QuickAccessSection(assistants = topAssistants)

        Spacer(modifier = Modifier.height(24.dp))

        TopicSuggestionsSection(topics = topicSuggestions)
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePagePreview() {
    MaterialTheme {
        WelcomePage()
    }
}
