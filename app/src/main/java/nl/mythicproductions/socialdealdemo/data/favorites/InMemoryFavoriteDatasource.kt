package nl.mythicproductions.socialdealdemo.data.favorites

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class InMemoryFavoriteDatasource @Inject constructor(): FavoriteDatasource {
    private val favorites = MutableStateFlow(emptyList<String>())

    override fun getFavorites(): List<String> {
        return favorites.value
    }

    override fun getFavoritesFlow(): Flow<List<String>> {
        return favorites.asStateFlow()
    }

    override fun addFavorite(favorite: String) {
        favorites.update {
            it + favorite
        }
    }

    override fun removeFavorite(favorite: String) {
        favorites.update {
            it - favorite
        }
    }
}