package nl.mythicproductions.socialdealdemo.data.deal

interface DealRepository {
    /**
     * Get all deals
     */
    suspend fun getDeals(): List<Deal>

    /**
     * Get deal by id
     */
    suspend fun getDealById(id: Int): Deal?
}