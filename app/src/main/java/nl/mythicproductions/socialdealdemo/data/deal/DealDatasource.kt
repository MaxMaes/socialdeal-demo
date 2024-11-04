package nl.mythicproductions.socialdealdemo.data.deal

interface DealDatasource {
    /**
     * Load all deals from the datasource
     *
     * @return A list of all deals, can be empty if no deals are found
     */
    fun loadDeals(): List<Deal>

    /**
     * Load a deal by its id
     *
     * @param id The id of the deal
     * @return The deal with the given id or null if no deal was found
     */
    fun loadDealById(id: Int): Deal?
}