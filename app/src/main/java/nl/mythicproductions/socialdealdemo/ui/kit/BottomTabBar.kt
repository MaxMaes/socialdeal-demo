package nl.mythicproductions.socialdealdemo.ui.kit

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import nl.mythicproductions.socialdealdemo.ui.navigation.RootRoute

@Composable
fun BottomTabBar(rootRoutes: List<RootRoute<out Any>>, navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        rootRoutes.forEach { rootRoute ->
            val isSelected = currentDestination?.hierarchy?.any { it.hasRoute(rootRoute.route::class) } == true
            NavigationBarItem(
                icon = {
                    Icon(
                        rootRoute.iconActive?.takeIf { isSelected } ?: rootRoute.icon,
                        contentDescription = rootRoute.name
                    )
                },
                label = { Text(rootRoute.name) },
                selected = isSelected,
                onClick = {
                    navController.navigate(rootRoute.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}