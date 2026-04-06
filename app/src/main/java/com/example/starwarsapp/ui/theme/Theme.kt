// ui/theme/StarWarsTheme.kt
package com.example.swapi.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Star Wars цвета
val JediBlue = Color(0xFF4A90E2)
val SithRed = Color(0xFFE94560)
val DarkSide = Color(0xFF0A0A0F)
val LightSaberBlue = Color(0xFF00D2FF)
val LightSaberGreen = Color(0xFF39FF14)
val ImperialGray = Color(0xFF2C2C3A)
val GoldCredits = Color(0xFFFFD700)
val HoloBlue = Color(0xFF00E5FF)

// Используем стандартные шрифты вместо кастомных
private val DarkColorScheme = darkColorScheme(
    primary = SithRed,
    secondary = JediBlue,
    tertiary = GoldCredits,
    background = DarkSide,
    surface = ImperialGray,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = HoloBlue,
    onSurface = Color.White,
    primaryContainer = Color(0xFF1A1A2E),
    onPrimaryContainer = Color.White
)

@Composable
fun StarWarsTheme(
    darkTheme: Boolean = true, // Всегда тёмная тема как в ЗВ
    content: @Composable () -> Unit
) {
    val typography = Typography(
        displayLarge = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        ),
        titleLarge = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 24.sp,
            letterSpacing = 1.5.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontSize = 16.sp
        )
    )

    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = typography,
        content = content
    )
}