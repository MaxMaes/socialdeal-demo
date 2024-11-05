package nl.mythicproductions.socialdealdemo.data.deal

import nl.mythicproductions.socialdealdemo.data.favorites.FavoriteDatasource
import javax.inject.Inject

class DealRepositoryImpl @Inject constructor(
    private val datasource: DealDatasource,
    private val favoritesDatasource: FavoriteDatasource
) : DealRepository {
    override suspend fun getDeals(): List<Deal> {
        val favorites = favoritesDatasource.getFavorites()
        return datasource.loadDeals().map {
            it.copy(isFavorite = favorites.contains(it.unique))
        }
    }

    override suspend fun getDealById(id: String): Deal? {
        val favorite = datasource.loadDealById(id)
        if (favorite != null) {
            val favorites = favoritesDatasource.getFavorites()
            return favorite.copy(isFavorite = favorites.contains(favorite.unique))
        } else {
            return null
        }
    }
}