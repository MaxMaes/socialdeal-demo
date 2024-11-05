package nl.mythicproductions.socialdealdemo.ui.navigation

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
            BottomTabBar(rootRoutes = bottomBarRoutes, navController = navController)
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