package nl.mythicproductions.socialdealdemo.data.deal

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import nl.mythicproductions.socialdealdemo.DealsClient
import javax.inject.Inject

@Serializable
data class DealListResponse(val deals: List<Deal>, val numDeals: Int)

class DealDatasourceImpl @Inject constructor(@DealsClient private val httpClient: HttpClient) :
    DealDatasource {
    override suspend fun loadDeals(): List<Deal> = withContext(Dispatchers.IO) {
        val response = httpClient.get {
            url("demo/deals.json")
        }.body<DealListResponse>()

        response.deals
    }

    override suspend fun loadDealById(id: String): Deal? {
        return null
    }
}