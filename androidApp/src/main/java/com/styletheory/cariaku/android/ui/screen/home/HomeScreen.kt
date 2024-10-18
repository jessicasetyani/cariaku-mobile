package com.styletheory.cariaku.android.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.styletheory.cariaku.android.R
import java.time.LocalTime

import androidx.compose.ui.graphics.Color
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onOpenChat: () -> Unit
) {

    Scaffold(
        topBar = {
            CustomTopAppBar()
        }
    ) { innerPadding ->
        ContentArea(modifier = Modifier.padding(innerPadding), username = "Jessica")
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar() {
    TopAppBar(
        title = {
            Text(text = "CariAku")
        }, navigationIcon = {
            IconButton(onClick = { /* Handle icon click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Menu Icon",
                    tint = Color.Unspecified
                )
            }
        },
        actions = {
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search icon"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun ContentArea(modifier: Modifier = Modifier, username: String) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingSection(username = username)
    }
}

@Composable
fun GreetingSection(
    username: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${getGreeting()}, $username!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Masih semangat nih? CariAku siap bantu kamu 24/7 loh!",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

fun getGreeting(time: LocalTime = LocalTime.now()): String {
    return when(time.hour) {
        in 5..11 -> "Good Morning"
        in 12..16 -> "Good Afternoon"
        in 17..20 -> "Good Evening"
        else -> "Good Night"
    }
}