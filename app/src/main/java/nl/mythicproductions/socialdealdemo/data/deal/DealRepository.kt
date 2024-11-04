package nl.mythicproductions.socialdealdemo.data.deal

interface DealRepository {
    /**
     * Get all deals
     */
    fun getDeals(): List<Deal>

    /**
     * Get deal by id
     */
    fun getDealById(id: Int): Deal?
}