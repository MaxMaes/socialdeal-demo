package nl.mythicproductions.socialdealdemo.data.deal

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DealModule {
    @Binds
    abstract fun bindDealDatasource(datasource: DealDatasourceImpl): DealDatasource

    @Binds
    abstract fun bindDealRepository(repository: DealRepositoryImpl): DealRepository
}