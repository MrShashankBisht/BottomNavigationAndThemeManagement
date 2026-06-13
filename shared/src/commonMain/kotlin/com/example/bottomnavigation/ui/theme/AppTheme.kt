package com.example.bottomnavigation.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

// 1. Semantic Color Token Contract
@Immutable
data class AppColors(
    val background: Color,
    val surface: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val navBackground: Color,
    val navIndicator: Color,
    val navSelectedIcon: Color,
    val navUnselectedIcon: Color,
    val isDark: Boolean,
    val navigation3IconBackgroundColor: Color
)

// 2. Typography Token Contract
@Immutable
data class AppTypography(
    val bodyLarge: TextStyle,
    val navLabel: TextStyle
)


// Raw Branding Hex Values
private val DarkBlueGray = Color(0xFF1E1E24)
private val BrandIndigo = Color(0xFF3F51B5)
private val CoreDark = Color(0xFF121212)
private val CoreWhite = Color(0xFFFFFFFF)
private val LightBg = Color(0xFFF5F5F7)

// Light Theme Implementation
val LightAppColors = AppColors(
    background = LightBg,
    surface = CoreWhite,
    textPrimary = Color(0xFF1C1B1F),
    textSecondary = BrandIndigo,
    navBackground = CoreWhite,
    navIndicator = Color.Transparent,
    navSelectedIcon = BrandIndigo,
    navUnselectedIcon = Color.Gray,
    isDark = false,
    navigation3IconBackgroundColor = Color(0x37539DF3)
)

// Dark Theme Implementation
val DarkAppColors = AppColors(
    background = CoreDark,
    surface = DarkBlueGray,
    textPrimary = CoreWhite,
    textSecondary = Color.White,
    navBackground = DarkBlueGray,
    navIndicator = Color.Transparent,
    navSelectedIcon = Color.White,
    navUnselectedIcon = Color.White,
    isDark = true,
    navigation3IconBackgroundColor = Color(0x37ffffff)
)


// Create a globally unified theme anchor object
object AppTheme {
    // Build local access hooks with fallbacks
    val LocalAppColors = staticCompositionLocalOf { LightAppColors }
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
}
