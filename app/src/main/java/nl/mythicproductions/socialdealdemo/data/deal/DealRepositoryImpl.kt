package nl.mythicproductions.socialdealdemo.data.deal

import javax.inject.Inject

class DealRepositoryImpl @Inject constructor(val datasource: DealDatasource) : DealRepository {
    override suspend fun getDeals(): List<Deal> {
        return datasource.loadDeals()
    }

    override suspend fun getDealById(id: String): Deal? {
        return datasource.loadDealById(id)
    }
}