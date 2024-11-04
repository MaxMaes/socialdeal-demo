package nl.mythicproductions.socialdealdemo.data.deal

import kotlinx.serialization.Serializable

/**
 * {
 *       "unique": "x6ji36jvyi4mj9fk",
 *       "title": "Bioscoopticket + popcorn + drankje bij Corendon Cinema",
 *       "image": "/deal/corendon-village-hotel-amsterdam-22113009143271.jpg",
 *       "sold_label": "Verkocht: 19",
 *       "company": "Corendon Village Hotel Amsterdam",
 *       "city": "Badhoevedorp (7 km)",
 *       "prices": {
 *         "price": {
 *           "amount": 1250,
 *           "currency": {
 *             "symbol": "€",
 *             "code": "EUR"
 *           }
 *         },
 *         "from_price": {
 *           "amount": 1700,
 *           "currency": {
 *             "symbol": "€",
 *             "code": "EUR"
 *           }
 *         },
 *         "price_label": null,
 *         "discount_label": "26%"
 *       }
 *     }
 */
@Serializable
// Generate a data class for this JSON structure
data class Deal(
    /**
     * Unique identifier for the deal
     */
    val unique: String,
    /**
     * Title of the deal
     */
    val title: String,
    /**
     * Description of the deal
     * Only available from the detail endpoint
     */
    val description: String = "",
    /**
     * Image of the deal
     */
    val image: String,
    /**
     * Label for the amount of deals sold
     */
    val soldLabel: String,
    /**
     * Company that offers the deal
     */
    val company: String,
    /**
     * City where the deal is offered, includes distance to user for demo purposes
     */
    val city: String,
    /**
     * Prices for the deal
     */
    val prices: Prices
)

val Deal.imageUrl: String
    // TODO: Hard-coded base URL, should be dynamic, ideally env based
    get() = "https://images.socialdeal.nl$image"

@Serializable
data class Prices(
    /**
     * Price of the deal
     */
    val price: Price,
    /**
     * Base price of the deal
     */
    val fromPrice: Price?
)

@Serializable
data class Price(
    val amount: Int,
    val currency: Currency
)

@Serializable
data class Currency(
    val symbol: String,
    val code: String
)

/**
 * Additional data for the deal, only available from the detail endpoint
 */
@Serializable
data class DealDescription(
    val description: String
)