package com.example.kotlinconsecutivepractices.presentation.navigation

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.kotlinconsecutivepractices.presentation.utils.FiltersPinCache

@Composable
fun AppBottomNavigationBar(
    navController: NavController,
    filtersPinCache: FiltersPinCache
) {
    val bottomRoutes = remember {
        listOf(
            BottomBarItems.GamesList,
            BottomBarItems.Filters,
            BottomBarItems.Favorites,
            BottomBarItems.Profile
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
                    BadgedBox(
                        badge = {
                            if (filtersPinCache.isFiltersUsed() && bottomBarItem.route == FiltersRoute)
                            {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            bottomBarItem.icon,
                            bottomBarItem.name
                        )
                    }
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
