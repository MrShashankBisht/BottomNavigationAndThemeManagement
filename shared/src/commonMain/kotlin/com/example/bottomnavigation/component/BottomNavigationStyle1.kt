package com.example.bottomnavigation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation.ui.theme.SimpleNavigationScreens
import com.example.bottomnavigation.ui.theme.AppTheme
import org.jetbrains.compose.resources.painterResource


@Composable
fun BottomNavigationStyle1(navController : NavController) {
    val bottomNavigationItems = listOf(SimpleNavigationScreens.Home, SimpleNavigationScreens.Search,
        SimpleNavigationScreens.Analytics, SimpleNavigationScreens.History, SimpleNavigationScreens.Profile)

    NavigationBar(
        containerColor = AppTheme.colors.navBackground,
        tonalElevation = 10.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: SimpleNavigationScreens.Home.route

        bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painterResource(item.icon), contentDescription = "bottom navigation item icon") },
                label = {
                    Text(item.title,
                        textAlign = TextAlign.Right)
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to avoid building up a large stack of destinations
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true

                        restoreState = true
                    }
                },
                // Style Change 3: Explicit state behavior using color overrides
                colors = NavigationBarItemDefaults.colors(
                    // Capsule background pill colors
                    indicatorColor = AppTheme.colors.navIndicator,

                    // Selected active element color tokens
                    selectedIconColor = AppTheme.colors.navSelectedIcon,
                    selectedTextColor = AppTheme.colors.textSecondary,

                    // Idle unselected element color tokens
                    unselectedIconColor = AppTheme.colors.navUnselectedIcon,
                    unselectedTextColor = AppTheme.colors.textSecondary
                )
            )
        }
    }
}



@Preview
@Composable
fun PreviewBottomNavigationStyle1() {
    val navController = rememberNavController()
    BottomNavigationStyle1(navController)
}