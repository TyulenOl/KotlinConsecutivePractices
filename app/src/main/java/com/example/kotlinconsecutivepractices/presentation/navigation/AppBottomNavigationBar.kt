package com.example.kotlinconsecutivepractices.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun AppBottomNavigationBar(
    navController: NavController
) {
    val bottomRoutes = remember {
        listOf(
            BottomBarItems.GamesList,
            BottomBarItems.Favorites,
            BottomBarItems.Settings
        )
    }

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomRoutes.forEach { bottomBarItem ->
            val isSelected = currentDestination?.hierarchy?.any { it.hasRoute(bottomBarItem.route::class) } == true

            NavigationBarItem(
                selected = isSelected,
                icon = {
                    Icon(
                        bottomBarItem.icon,
                        bottomBarItem.name
                    )
                },
                alwaysShowLabel = false,
                label = {
                    Text(bottomBarItem.name)
                },
                onClick = {
                    navController.navigate(bottomBarItem.route)
                }
            )
        }
    }
}

@Preview
@Composable
fun AppBottomNavigationBarPreview() {
    Scaffold(
        bottomBar = {
            AppBottomNavigationBar(
                navController = rememberNavController()
            )
        }
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues))
    }

}
