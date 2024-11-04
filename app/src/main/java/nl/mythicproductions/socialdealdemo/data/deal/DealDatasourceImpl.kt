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
    private var cachedData = emptyMap<String, Deal>()

    override suspend fun loadDeals(): List<Deal> = withContext(Dispatchers.IO) {
        val response = httpClient.get {
            url("demo/deals.json")
        }.body<DealListResponse>()

        cachedData = response.deals.associateBy { it.unique }

        response.deals
    }

    override suspend fun loadDealById(id: String): Deal? {
        // Not used in the demo
        val deal = if (id in cachedData) {
            cachedData[id]
        } else {
            loadDeals()
            cachedData[id]
        }
        if (deal != null) {
            // Load the description for the deal, it's not included in the default list call
            val description = loadDealDescription(deal.unique)
            return deal.copy(
                description = description.description
            )
        }

        return null
    }

    private suspend fun loadDealDescription(id: String): DealDescription =
        withContext(Dispatchers.IO) {
            // Id is not used in the demo
            httpClient.get {
                url("demo/details.json?id=$id")
            }.body<DealDescription>()
        }
}