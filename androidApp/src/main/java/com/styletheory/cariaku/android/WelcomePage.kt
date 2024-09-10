package com.styletheory.cariaku.android

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun QuickAccessSection(assistants: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "CariAku Andalan", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            assistants.forEach { assistant ->
                OutlinedCard(
                    modifier = Modifier
                        .clickable(onClick = { /* Start conversation with assistant */ })
                        .width(IntrinsicSize.Min),
                    colors = CardDefaults.outlinedCardColors(
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_placeholder_assistant), // Replace with assistant image
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(MaterialTheme.shapes.small)
                                .padding(bottom = 4.dp)
                        )
                        Text(
                            text = assistant,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(8.dp)
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "CariAku Apa Hari Ini?", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(topics) { topic ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable(onClick = { /* Start conversation with topic suggestion */ })
                        .width((0.5f * LocalConfiguration.current.screenWidthDp).dp)
                        .padding(end = 8.dp),
                    colors = CardDefaults.cardColors(
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(
                        text = topic,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RecentChatsSection(chats: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "CariAku Lagi", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "CariAku selalu siap lanjut ngobrol 24/7!", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        chats.take(10).forEach { chat ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable(onClick = { /* Continue chat */ }),
                colors = CardDefaults.elevatedCardColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Row {
                    Text(
                        text = chat,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItemIndex: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = index == selectedItemIndex,
                onClick = { onItemClick(index) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    }
}

data class BottomNavigationItem(
    val icon: ImageVector,
    val label: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomePage() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedItemIndex by remember { mutableStateOf(0) }

    val topAssistants = listOf("Assistant 1", "Assistant 2", "Assistant 3", "Assistant 4")
    val topicSuggestions = listOf(
        "Cara hemat uang jajan? CariAku tau nih!",
        "Mau tau rekomendasi film seru buat ditonton?",
        "Pengen dapet inspirasi buat dekorasi kamar?"
    )
    val recentChats = listOf(
        "Chat 1: How to save money?",
        "Chat 2: Movie recommendations?",
        "Chat 3: Room decoration ideas?",
        "Chat 4: Best restaurants nearby?",
        "Chat 5: Workout routines?",
        "Chat 6: How to save money?",
        "Chat 7: Movie recommendations?",
        "Chat 8: Room decoration ideas?",
        "Chat 9: Best restaurants nearby?",
        "Chat 10: Workout routines?"
    )
    val colors1 = SearchBarDefaults.colors()
    val onActiveChange: (Boolean) -> Unit = { /* Handle active change */ }

    val bottomNavigationItems = listOf(
        BottomNavigationItem(icon = Icons.Default.Home, label = "Home"),
        BottomNavigationItem(icon = Icons.Default.Favorite, label = "Favorites"),
        BottomNavigationItem(icon = Icons.Default.Person, label = "Profile")
    )

    SearchBar(
        query = searchQuery,
        onQueryChange = { searchQuery = it },
        onSearch = { /* Handle search */ },
        active = false,
        onActiveChange = onActiveChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .zIndex(1f),
        placeholder = { Text("Cari Apa Nih? CariAku") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        trailingIcon = null,
        colors = colors1,
        content = {
            // Search suggestions can be added here
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 56.dp, bottom = 56.dp) // Adjust this value based on the height of the SearchBar and BottomNavigationBar
        ) {
            item {
                TopicSuggestionsSection(topics = topicSuggestions)
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                QuickAccessSection(assistants = topAssistants)
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                RecentChatsSection(chats = recentChats)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
    ) {
        BottomNavigationBar(
            items = bottomNavigationItems,
            selectedItemIndex = selectedItemIndex,
            onItemClick = { selectedItemIndex = it }
        )
    }
}

@Composable
fun WelcomeHeader() {
    Text(
        text = "Malam, [nama user]! Masih semangat nih? CariAku siap bantu kamu 24/7 loh!",
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun WelcomePagePreview() {
    MaterialTheme {
        WelcomePage()
    }
}
