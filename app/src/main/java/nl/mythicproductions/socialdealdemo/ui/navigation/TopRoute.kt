package nl.mythicproductions.socialdealdemo.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

data class RootRoute<T : Any>(val name: String, val route: T, val icon: ImageVector, val iconActive: ImageVector? = null)

@Serializable
object DealsRoute

@Serializable
object FavoritesRoute

@Serializable
object SettingsRoute

val bottomBarRoutes = listOf(
    RootRoute("Deals", DealsRoute, Icons.Default.ShoppingCart),
    RootRoute("Favorites", FavoritesRoute, Icons.Default.FavoriteBorder, Icons.Default.Favorite),
    RootRoute("Settings", SettingsRoute, Icons.Default.Settings)
)
