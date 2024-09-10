package com.styletheory.cariaku.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFFFFFFFF), // Active Button
            secondary = Color(0x14FFFFFF), // Hover Button
            tertiary = Color(0x28FFFFFF), // Selected Button
            background = Color(0xFF121212), // Main Background
            surface = Color(0xFF2F3438), // Secondary Background
            onPrimary = Color(0xFFFFFFFF), // Primary Text
            onSecondary = Color(0xB3FFFFFF), // Secondary Text
            onBackground = Color(0xFFFFFFFF), // Primary Text
            onSurface = Color(0xB3FFFFFF), // Secondary Text
            error = Color(0xFF000000), // Shadow
            onError = Color(0xFF000000), // Shadow
            onSurfaceVariant = Color(0x66FFFFFF), // Disabled Text
            surfaceVariant = Color(0x1EFFFFFF), // Disabled Button Background
            onSurfaceVariantInverse = Color(0x08FFFFFF), // Hover Item Background
            inverseSurface = Color(0x08FFFFFF) // Hover Item Background
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF000000), // Active Button
            secondary = Color(0xFFF1F1EF), // Hover Button
            tertiary = Color(0xFFE7F3F8), // Selected Button
            background = Color(0xFFFFFFFF), // Main Background
            surface = Color(0xFFF7F6F3), // Secondary Background
            onPrimary = Color(0xFF000000), // Primary Text
            onSecondary = Color(0xFF37352F), // Secondary Text
            onBackground = Color(0xFF000000), // Primary Text
            onSurface = Color(0xFF37352F), // Secondary Text
            error = Color(0xFF000000), // Shadow
            onError = Color(0xFF000000), // Shadow
            onSurfaceVariant = Color(0xFF787774), // Disabled Text
            surfaceVariant = Color(0xFFF4EEEE), // Disabled Button Background
            onSurfaceVariantInverse = Color(0xFFFFFFFF), // Hover Item Background
            inverseSurface = Color(0xFFFFFFFF) // Hover Item Background
        )
    }
    val typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
