package com.example.bottomnavigation.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation.component.BottomNavigationStyle4
import com.example.bottomnavigation.component.BottomNavigationBar3
import com.example.bottomnavigation.component.CuteThemeToggle
import com.example.bottomnavigation.component.BottomNavigationStyle1
import com.example.bottomnavigation.component.BottomNavigationStyle2

@Composable
fun CommonScreen(navController: NavController, changeTheme : ((isDarkTheme : Boolean) -> Unit)) {
    Column {
        // Render our cute interactive engine component
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Change Theme ")
            Spacer(modifier = Modifier.size(10.dp))
            CuteThemeToggle(
                isDark = AppTheme.colors.isDark,
                onThemeToggle = { targetState ->
                    changeTheme(targetState)
                }
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        BottomNavigationStyle1(navController)

        Spacer(modifier = Modifier.size(10.dp))
        BottomNavigationStyle2(navController)

        Spacer(modifier = Modifier.size(10.dp))
        BottomNavigationBar3(navController)


    }
}


@Preview
@Composable
fun PreviewCommonScreen() {
    CommonScreen(rememberNavController()) {

    }
}