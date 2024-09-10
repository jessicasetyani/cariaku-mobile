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

/**
 * A composable function that defines the application's theme.
 *
 * @param darkTheme A boolean indicating whether the dark theme should be used. Defaults to the system's dark theme setting.
 * @param content The content to be displayed within the theme.
 */
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if(darkTheme) {
        darkColorScheme(
            primary = Color(0xFF4a7bc4), // Active Button
            secondary = Color(0xFF6a93d4), // Hover Button
            tertiary = Color(0xFF8ca6d5), // Selected Button
            background = Color(0xFF121212), // Main Background
            surface = Color(0xFF333333), // Secondary Background
            onPrimary = Color(0xFFffffff), // Primary Text
            onSecondary = Color(0xFFffffff), // Secondary Text
            onBackground = Color(0xFFffffff), // Primary Text
            onSurface = Color(0xFFffffff), // Secondary Text
            error = Color(0xFFff6b6b), // Shadow
            onError = Color(0xFFffffff), // Shadow
            onSurfaceVariant = Color(0xFFbdbdbd), // Disabled Text
            surfaceVariant = Color(0xFF424242), // Disabled Button Background
            inverseOnSurface = Color(0xFFffffff), // Hover Item Background
            inverseSurface = Color(0xFFe0e0e0) // Hover Item Background
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF6a93d4), // Active Button
            secondary = Color(0xFF8ca6d5), // Hover Button
            tertiary = Color(0xFF4a7bc4), // Selected Button
            background = Color(0xFFf0f4f8), // Main Background
            surface = Color(0xFFffffff), // Secondary Background
            onPrimary = Color(0xFFffffff), // Primary Text
            onSecondary = Color(0xFF000000), // Secondary Text
            onBackground = Color(0xFF000000), // Primary Text
            onSurface = Color(0xFF000000), // Secondary Text
            error = Color(0xFFff6b6b), // Shadow
            onError = Color(0xFFffffff), // Shadow
            onSurfaceVariant = Color(0xFF747474), // Disabled Text
            surfaceVariant = Color(0xFFe0e0e0), // Disabled Button Background
            inverseOnSurface = Color(0xFFe0e0e0), // Hover Item Background
            inverseSurface = Color(0xFF2c2c2c) // Hover Item Background
        )
    }
    /**
     * Defines the typography for the application.
     */
    val typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    /**
     * Defines the shapes for the application.
     */
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )
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
