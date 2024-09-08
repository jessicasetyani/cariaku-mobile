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
import androidx.compose.ui.unit.sp

@Composable
fun WelcomePage() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cariaku") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
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
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
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

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = modifier,
        placeholder = { Text("Search...") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        singleLine = true
    )
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
        Icon(icon, contentDescription = label, modifier = Modifier.size(48.dp))
        Text(text = label, fontSize = 14.sp)
    }
}

@Composable
fun ItemList() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Featured Items",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        repeat(5) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.ShoppingBag,
                        contentDescription = "Item",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Item ${it + 1}", fontSize = 16.sp)
                }
            }
        }
    }
}
