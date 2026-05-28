package com.example.diarioentrenamiento.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val CineLogDarkColorScheme = darkColorScheme(
    primary        = Color(0xFF7C3AED),
    secondary      = Color(0xFFEC4899),
    tertiary       = Color(0xFFFFD700),
    background     = Color(0xFF0D0D1A),
    surface        = Color(0xFF1A1A2E),
    onPrimary      = Color.White,
    onBackground   = Color(0xFFF1F5F9),
    onSurface      = Color(0xFFF1F5F9),
)

@Composable
fun DiarioEntrenamientoTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color(0xFF12122A).toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = CineLogDarkColorScheme,
        typography  = Typography,
        content     = content
    )
}
