package nl.mythicproductions.socialdealdemo.data.deal

import javax.inject.Inject

class DealRepositoryImpl @Inject constructor(val datasource: DealDatasource) : DealRepository {
    override fun getDeals(): List<Deal> {
        return datasource.loadDeals()
    }

    override fun getDealById(id: Int): Deal? {
        return datasource.loadDealById(id)
    }
}