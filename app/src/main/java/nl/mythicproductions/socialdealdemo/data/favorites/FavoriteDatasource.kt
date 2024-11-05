package nl.mythicproductions.socialdealdemo.data.favorites

import kotlinx.coroutines.flow.Flow

/**
 * Interface for a datasource that provides access to the user's favorite items.
 * Uses identifiers to represent the favorites.
 */
interface FavoriteDatasource {
    fun getFavorites(): List<String>
    fun getFavoritesFlow(): Flow<List<String>>
    fun addFavorite(favorite: String)
    fun removeFavorite(favorite: String)
}