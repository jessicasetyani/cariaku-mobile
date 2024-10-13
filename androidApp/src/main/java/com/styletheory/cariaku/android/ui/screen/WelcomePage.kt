package com.styletheory.cariaku.android.ui.screen

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
import androidx.compose.material.icons.filled.Clear
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.styletheory.cariaku.android.R

/**
 * A composable function that displays a section for quick access to frequently used assistants.
 *
 * @param assistants A list of assistant names to be displayed.
 */
@Composable
fun QuickAccessSection(
    assistants: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "CariAku Andalan", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
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
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_placeholder_assistant), // Replace with assistant image
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(MaterialTheme.shapes.small)
                                .padding(top = 8.dp)
                        )
                        Text(
                            text = assistant,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * A composable function that displays a section for topic suggestions.
 *
 * @param topics A list of topic suggestions to be displayed.
 */
@Composable
fun TopicSuggestionsSection(topics: List<String>, onOpenChat: () -> Unit) {
    val listState = rememberLazyListState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "CariAku Apa Hari Ini?", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(topics) { topic ->
                Card(
                    onClick = onOpenChat,
                    modifier = Modifier
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

/**
 * A composable function that displays a section for recent chats.
 *
 * @param topics A list of recent chat topics.
 * @param summaries A list of summaries for each recent chat.
 * @param times A list of times indicating when each chat was last active.
 */
@Composable
fun RecentChatsSection(topics: List<String>, summaries: List<String>, times: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "CariAku Lagi", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "CariAku selalu siap lanjut ngobrol 24/7!", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        topics.zip(summaries).forEach { (chat, summary) ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable(onClick = { /* Continue chat */ }),
                colors = CardDefaults.elevatedCardColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = chat,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(
                        text = summary,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    Text(
                        text = times[topics.indexOf(chat)],
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

/**
 * A composable function that displays a bottom navigation bar.
 *
 * @param items A list of navigation items to be displayed.
 * @param selectedItemIndex The index of the currently selected item.
 * @param onItemClick A callback function to handle item click events.
 */
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

/**
 * The main composable function that represents the welcome page of the application.
 * It includes various sections like quick access, recent chats, and topic suggestions.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomePage(
    onOpenChat: () -> Unit,
    navController: NavController
) {
    WelcomePageContent(onOpenChat, navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomePageContent(
    onOpenChat: () -> Unit,
    navController: NavController
) {
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
        "Chat 2: Movie recommendations?"
    )
    val chatSummaries = listOf(
        "This is summaries of How to save money?",
        "This is summaries of Movie recommendations?"
    )
    val times = List(recentChats.size) { "Diskusi Terakhir 1 jam yang lalu" }
    val searchBarColors = SearchBarDefaults.colors()
    val onActiveChange: (Boolean) -> Unit = { /* Handle active change */ }

    val bottomNavigationItems = listOf(
        BottomNavigationItem(Icons.Default.Home, "Home"),
        BottomNavigationItem(Icons.Default.Favorite, "Favorites"),
        BottomNavigationItem(Icons.Default.Person, "Profile")
    )

    // Search suggestions can be added here
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { /* Handle search */ },
                expanded = false,
                onExpandedChange = onActiveChange,
                placeholder = { Text("Cari Apa Nih? CariAku") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if(searchQuery.isNotEmpty()) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Clear Search",
                            modifier = Modifier.clickable { searchQuery = "" }
                        )
                    }
                },
                colors = searchBarColors.inputFieldColors,
            )
        },
        expanded = false,
        onExpandedChange = onActiveChange,
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(1f),
        colors = searchBarColors,
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
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 86.dp,
                    bottom = 16.dp
                ) // Adjust this value based on the height of the SearchBar and BottomNavigationBar
        ) {
            item {
                Spacer(Modifier.height(16.dp))
                WelcomeHeader()
                Spacer(Modifier.height(16.dp))
            }

            item {
                QuickAccessSection(topAssistants)
                Spacer(Modifier.height(14.dp))
            }

            item {
                RecentChatsSection(recentChats, chatSummaries, times)
                Spacer(Modifier.height(16.dp))
            }

            item {
                TopicSuggestionsSection(topicSuggestions, onOpenChat)
                Spacer(Modifier.height(16.dp))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            BottomNavigationBar(
                bottomNavigationItems,
                selectedItemIndex,
                { selectedItemIndex = it }
            )
        }
    }
}

/**
 * A composable function that displays a welcome header with a greeting message.
 */
@Composable
fun WelcomeHeader() {
    Text(
        text = "Malam, [nama user]! Masih semangat nih? CariAku siap bantu kamu 24/7 loh!",
        style = MaterialTheme.typography.headlineSmall
    )
}
