package com.styletheory.cariaku.android

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomePage() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text("Cariaku") },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Chat, contentDescription = "Chat") },
                    label = { Text("Chat") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Search, contentDescription = "Discover") },
                    label = { Text("Discover") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Text(
                text = "Welcome, User!",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
            QuickAccessSection()
            ItemList()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    androidx.compose.material3.SearchBar(
        query = text,
        onQueryChange = { text = it },
        onSearch = { active = false },
        active = active,
        onActiveChange = { active = it },
        placeholder = { Text("Search...") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        modifier = modifier
    ) {
        // Search suggestions can be added here
    }
}

@Composable
fun QuickAccessSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Quick Access",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            QuickAccessItem(icon = Icons.Filled.Favorite, label = "Favorites")
            QuickAccessItem(icon = Icons.Filled.ShoppingCart, label = "Cart")
            QuickAccessItem(icon = Icons.Filled.Person, label = "Profile")
        }
    }
}

@Composable
fun QuickAccessItem(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FilledIconButton(
            onClick = { /* Handle click */ },
            modifier = Modifier.size(56.dp)
        ) {
            Icon(icon, contentDescription = label, modifier = Modifier.size(24.dp))
        }
        Text(text = label, style = MaterialTheme.typography.labelMedium)
    }
}

@Composable
fun ItemList() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Featured Items",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        repeat(5) {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
            ) {
                ListItem(
                    headlineContent = { Text("Item ${it + 1}") },
                    leadingContent = {
                        Icon(
                            Icons.Filled.ShoppingBag,
                            contentDescription = "Item",
                            modifier = Modifier.size(40.dp)
                        )
                    },
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
