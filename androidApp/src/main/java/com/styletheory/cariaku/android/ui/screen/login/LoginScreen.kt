package com.styletheory.cariaku.android.ui.screen.login

/**
 * Created by Jessica Setyani on 13-10-2024.
 */
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    val username = remember { mutableStateOf("") }

    Column {
        Text("Please enter your username:")
        BasicTextField(
            value = username.value,
            onValueChange = { username.value = it },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { navController.navigate("checkEmailScreen") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }
    }
}


}