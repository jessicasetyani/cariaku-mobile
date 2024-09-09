package com.styletheory.cariaku.android

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun QuickAccessSection(assistants: List<String>) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(text = "Quick Access (Asisten Andalan)", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow {
            items(assistants) { assistant ->
                Text(
                    text = assistant,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}
private fun SearchBarDefaults.InputField(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: () -> Unit,
    enabled: Boolean,
    placeholder: @Composable () -> Unit,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: Nothing?,
    colors: TextFieldColors,
    interactionSource: Nothing?
) {

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
