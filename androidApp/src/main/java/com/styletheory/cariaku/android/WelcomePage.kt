package com.styletheory.cariaku.android

import androidx.compose.foundation.layout.ColumnScope
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomePage() {
    var searchQuery by remember { mutableStateOf("") }

    val onActiveChange = { /* Handle active change */ }
    val colors1 = SearchBarDefaults.colors()
    // Search suggestions can be added here
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { /* Handle search */ },
                expanded = false,
                onExpandedChange = onActiveChange,
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
        content = fun ColumnScope.() {
            // Search suggestions can be added here
        },
    )
}

@Preview(showBackground = true)
@Composable
fun WelcomePagePreview() {
    MaterialTheme {
        WelcomePage()
    }
}
