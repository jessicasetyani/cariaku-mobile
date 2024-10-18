package com.styletheory.cariaku.android.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.styletheory.cariaku.android.ui.theme.ButtonBlue
import com.styletheory.cariaku.android.ui.theme.LightRed
import com.styletheory.cariaku.android.ui.theme.TextWhite

@Composable
fun CariAkuHistorySection(
    color: Color = LightRed
) {
    Text(text = "CariAku Lagi", style = MaterialTheme.typography.headlineSmall)
    Text(text = "CariAku selalu siap lanjut ngobrol 24/7!", modifier = Modifier.padding(top = 4.dp), style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(end = 12.dp, top = 8.dp, bottom = 16.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Meditation • 3-10 min",
                style = MaterialTheme.typography.bodySmall,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
