package com.example.bottomnavigation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun BottomNavigationStyle4(
    navController: NavController,
) {
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
        val currentRoute = navBackStackEntry?.destination?.route ?: SimpleNavigationScreens.Home.route
        val n = bottomNavigationItems.size.toFloat()
        bottomNavigationItems.forEach { item ->
            val isSelected = currentRoute == item.route
            val weight: Float = (if(isSelected && n > 0) { 2*(1/n) } else if(!isSelected && n > 3) { ((n-1)/(n*n)) } else { 1 }) as Float
            println("weight calculated : $weight")
            // 1. Clickable Outer Container to evenly distribute the bar width
            Row(
                modifier = Modifier
                    .weight(weight)
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple() // Uses the standard M3 touch ripple effect
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // 2. Inner Pill Container that grows and clips background color
                Row(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(if (isSelected) AppTheme.colors.navIndicator else Color.Transparent)
                        .padding(horizontal = 2.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    // 3. Icon handling sizing and color adjustments natively
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = "bottom navigation item icon",
                        modifier = Modifier.size(if (isSelected) 18.dp else 20.dp),
                        tint = if (isSelected) AppTheme.colors.navSelectedIcon else AppTheme.colors.navUnselectedIcon
                    )

                    // 4. Animated Side Expansion Container for the text
                    AnimatedVisibility(
                        visible = isSelected,
                        enter = fadeIn() + expandHorizontally(expandFrom = Alignment.Start),
                        exit = fadeOut() + shrinkHorizontally(shrinkTowards = Alignment.Start)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = item.title,
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                color = AppTheme.colors.textSecondary,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun previewCustomeNavigation() {
    BottomNavigationStyle4(rememberNavController())
}
