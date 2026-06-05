package com.example.bottomnavigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation.component.AppThemeWrapper
import com.example.bottomnavigation.component.BottomNavigationStyle4
import com.example.bottomnavigation.ui.theme.CommonScreen
import com.example.bottomnavigation.ui.theme.SimpleNavigationScreens

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    // Top-level dynamic application context state control
    val isSystemDark = isSystemInDarkTheme()
    var systemThemeIsDark by remember { mutableStateOf(isSystemDark) }
    AppThemeWrapper(systemThemeIsDark) {
        Scaffold(
            bottomBar = {
                BottomNavigationStyle4(navController)
            },

        ) { innerPadding ->
            NavHost(navController = navController, startDestination = SimpleNavigationScreens.Home.route, modifier = Modifier.padding(innerPadding)) {
                composable(SimpleNavigationScreens.Home.route) {

                    CommonScreen(navController) {
                        systemThemeIsDark = it
                    }
                }
                composable(SimpleNavigationScreens.Search.route) {
                    CommonScreen(navController) {
                        systemThemeIsDark = it
                    }
                }
                composable(SimpleNavigationScreens.Profile.route) {
                    CommonScreen(navController) {
                        systemThemeIsDark = it
                    }
                }
                composable(SimpleNavigationScreens.Analytics.route) {
                    CommonScreen(navController) {
                        systemThemeIsDark = it
                    }
                }
                composable(SimpleNavigationScreens.History.route) {
                    CommonScreen(navController) {
                        systemThemeIsDark = it
                    }
                }
            }
        }
    }
}
