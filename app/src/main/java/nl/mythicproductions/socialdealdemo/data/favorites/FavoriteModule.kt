package nl.mythicproductions.socialdealdemo.data.favorites

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class FavoriteModule {
    @Singleton
    @Binds
    abstract fun bindFavoriteDatasource(inMemoryFavoriteDatasource: InMemoryFavoriteDatasource): FavoriteDatasource

    @Binds
    abstract fun bindFavoriteRepository(favoriteRepositoryImpl: FavoriteRepositoryImpl): FavoriteRepository
}