package nl.mythicproductions.socialdealdemo.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import nl.mythicproductions.socialdealdemo.ui.kit.BottomTabBar
import nl.mythicproductions.socialdealdemo.ui.screens.dealDetail.DealDetailRoute
import nl.mythicproductions.socialdealdemo.ui.screens.dealDetail.DealDetailScreen
import nl.mythicproductions.socialdealdemo.ui.screens.deals.DealsScreen
import nl.mythicproductions.socialdealdemo.ui.screens.favorites.FavoritesScreen
import nl.mythicproductions.socialdealdemo.ui.screens.settings.SettingsScreen

@Composable
fun RootNavigator() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val isVisible = bottomBarRoutes.any { rootRoute ->
                currentDestination?.hierarchy?.any { it.hasRoute(rootRoute.route::class) } == true
            }

            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(initialOffsetY = { it / 2 }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                BottomTabBar(rootRoutes = bottomBarRoutes, navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DealsRoute,
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .padding(innerPadding)
        ) {
            composable<DealsRoute> { DealsScreen(navController = navController) }
            composable<FavoritesRoute> { FavoritesScreen(navController = navController) }
            composable<SettingsRoute> { SettingsScreen() }

            composable<DealDetailRoute> {
                DealDetailScreen(
                    navController = navController,
                    dealId = it.toRoute<DealDetailRoute>().dealId
                )
            }
        }
    }
}