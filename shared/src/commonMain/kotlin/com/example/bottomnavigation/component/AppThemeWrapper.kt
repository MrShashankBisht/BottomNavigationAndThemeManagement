package com.example.bottomnavigation.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.bottomnavigation.ui.theme.AppTheme.LocalAppColors
import com.example.bottomnavigation.ui.theme.DarkAppColors
import com.example.bottomnavigation.ui.theme.LightAppColors

@Composable
fun AppThemeWrapper(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val currentColors = if (darkTheme) DarkAppColors else LightAppColors

    // Map your custom enterprise layout tokens back to the baseline Material 3 layout
    val fallbackM3ColorScheme = if (darkTheme) {
        darkColorScheme(primary = currentColors.navSelectedIcon, background = currentColors.background)
    } else {
        lightColorScheme(primary = currentColors.navSelectedIcon, background = currentColors.background)
    }

    CompositionLocalProvider(
        LocalAppColors provides currentColors
    ) {
        MaterialTheme(
            colorScheme = fallbackM3ColorScheme,
            content = content
        )
    }
}
