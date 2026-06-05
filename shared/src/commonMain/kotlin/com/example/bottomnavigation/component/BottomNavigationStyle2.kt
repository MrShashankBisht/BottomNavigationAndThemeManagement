package com.example.bottomnavigation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation.ui.theme.SimpleNavigationScreens
import com.example.bottomnavigation.ui.theme.AppTheme
import org.jetbrains.compose.resources.painterResource


@Composable
fun BottomNavigationStyle2(navController: NavController) {
    val bottomNavigationItems = listOf(
        SimpleNavigationScreens.Home,
        SimpleNavigationScreens.Search,
        SimpleNavigationScreens.Analytics,
        SimpleNavigationScreens.History,
        SimpleNavigationScreens.Profile
    )

    NavigationBar(
        containerColor = AppTheme.colors.navBackground,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: SimpleNavigationScreens.Analytics.route
        bottomNavigationItems.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                modifier = Modifier.background(Color.Transparent),
                icon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.background(Color.Transparent)
                    ) {
                        Icon(
                            painterResource(item.icon),
                            contentDescription = "bottom navigation item icon",
                            modifier = Modifier.height(if (isSelected) 15.dp else 20.dp).width(if(isSelected) 15.dp else 20.dp)
                        )
                        if(isSelected){
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(item.title, textAlign = TextAlign.Center, fontSize = 12.sp, color = AppTheme.colors.textSecondary)
                        }
                    }

                },
                selected = isSelected,
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
fun PreviewBottomNavigationStyle2() {
    val navController = rememberNavController()
    BottomNavigationStyle2(navController)
}