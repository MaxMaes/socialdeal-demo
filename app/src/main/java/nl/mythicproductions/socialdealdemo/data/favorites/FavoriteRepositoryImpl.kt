package nl.mythicproductions.socialdealdemo.data.favorites

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDatasource: FavoriteDatasource
): FavoriteRepository {


    override fun getFavorites(): List<String> {
        return favoriteDatasource.getFavorites()
    }

    override fun getFavoritesFlow(): Flow<List<String>> {
        return favoriteDatasource.getFavoritesFlow()
    }

    override fun addFavorite(favorite: String) {
        favoriteDatasource.addFavorite(favorite)
    }

    override fun removeFavorite(favorite: String) {
        favoriteDatasource.removeFavorite(favorite)
    }
}