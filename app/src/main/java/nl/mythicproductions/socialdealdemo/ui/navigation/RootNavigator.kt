package nl.mythicproductions.socialdealdemo.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.mythicproductions.socialdealdemo.ui.kit.BottomTabBar
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
        NavHost(navController, startDestination = DealsRoute, Modifier.padding(innerPadding)) {
            composable<DealsRoute> { DealsScreen() }
            composable<FavoritesRoute> { FavoritesScreen() }
            composable<SettingsRoute> { SettingsScreen() }
        }
    }
}