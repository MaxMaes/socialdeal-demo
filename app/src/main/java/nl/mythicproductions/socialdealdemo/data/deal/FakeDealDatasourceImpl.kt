package nl.mythicproductions.socialdealdemo.data.deal

import javax.inject.Inject

class FakeDealDatasourceImpl @Inject constructor() : DealDatasource {
    override suspend fun loadDeals(): List<Deal> {
        return listOf(
            Deal(
                "x6ji36jvyi4mj9fk",
                "Bioscoopticket + popcorn + drankje bij Corendon Cinema",
                "/deal/corendon-village-hotel-amsterdam-22113009143271.jpg",
                "Verkocht: 19",
                "Corendon Village Hotel Amsterdam",
                "Badhoevedorp (7 km)",
                Prices(
                    Price(
                        1250,
                        Currency(
                            "€",
                            "EUR"
                        )
                    ),
                    Price(
                        1700,
                        Currency(
                            "€",
                            "EUR"
                        )
                    ),
                    "26%"
                )
            ),
            Deal(
                "x6ji36jvyi4mj9fk",
                "Bioscoopticket + popcorn + drankje bij Corendon Cinema",
                "/deal/corendon-village-hotel-amsterdam-22113009143271.jpg",
                "Verkocht: 19",
                "Corendon Village Hotel Amsterdam",
                "Badhoevedorp (7 km)",
                Prices(
                    Price(
                        1250,
                        Currency(
                            "€",
                            "EUR"
                        )
                    ),
                    Price(
                        1700,
                        Currency(
                            "€",
                            "EUR"
                        )
                    ),
                    "26%"
                )
            ),
            Deal(
                "x6ji36jvyi4mj9fk",
                "Bioscoopticket + popcorn + drankje bij Corendon Cinema",
                "/deal/corendon-village-hotel-amsterdam-22113009143271.jpg",
                "Verkocht: 19",
                "Corendon Village Hotel Amsterdam",
                "Badhoevedorp (7 km)",
                Prices(
                    Price(
                        1250,
                        Currency(
                            "€",
                            "EUR"
                        )
                    ),
                    Price(
                        1700,
                        Currency(
                            "€",
                            "EUR"
                        )
                    ),
                    "26%"
                )
            ),
        )
    }

    override suspend fun loadDealById(id: Int): Deal? {
        return null
    }
}