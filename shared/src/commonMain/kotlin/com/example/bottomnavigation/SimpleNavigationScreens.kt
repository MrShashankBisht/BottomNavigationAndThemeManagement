package com.example.bottomnavigation

import bottomnavigation.shared.generated.resources.Res
import bottomnavigation.shared.generated.resources.icon_analytics
import bottomnavigation.shared.generated.resources.icon_history
import bottomnavigation.shared.generated.resources.icon_home
import bottomnavigation.shared.generated.resources.icon_profile
import bottomnavigation.shared.generated.resources.icon_search
import org.jetbrains.compose.resources.DrawableResource

sealed class SimpleNavigationScreens(val route: String, val title: String, val icon: DrawableResource) {
    data object Home : SimpleNavigationScreens("home", "Home", Res.drawable.icon_home)
    data object Search : SimpleNavigationScreens("search", "Search", Res.drawable.icon_search)
    data object Analytics : SimpleNavigationScreens("analytics", "Analytics", Res.drawable.icon_analytics)
    data object History : SimpleNavigationScreens("history", "History", Res.drawable.icon_history)
    data object Profile : SimpleNavigationScreens("profile", "Profile", Res.drawable.icon_profile)
}

sealed class SelectionNavigationScreen(val route: String, val title: String, val icon: DrawableResource) {
    data object Home : SimpleNavigationScreens("home", "Home", Res.drawable.icon_home)
    data object Search : SimpleNavigationScreens("search", "Search", Res.drawable.icon_search)
    data object Analytics : SimpleNavigationScreens("analytics", "Analytics", Res.drawable.icon_analytics)
    data object History : SimpleNavigationScreens("history", "History", Res.drawable.icon_history)
    data object Profile : SimpleNavigationScreens("profile", "Profile", Res.drawable.icon_profile)
}