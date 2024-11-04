package nl.mythicproductions.socialdealdemo.ui.kit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nl.mythicproductions.socialdealdemo.data.deal.Currency
import nl.mythicproductions.socialdealdemo.data.deal.Deal
import nl.mythicproductions.socialdealdemo.data.deal.Price
import nl.mythicproductions.socialdealdemo.data.deal.Prices
import nl.mythicproductions.socialdealdemo.ui.theme.SocialDealDemoTheme

@Composable
fun DealCard(
    deal: Deal,
    isFavorite: Boolean = false,
    onFavoriteClicked: () -> Unit = {},
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = spacedBy(8.dp),
        modifier = Modifier
            .clickable(onClick = onClick)
            .then(modifier)
    ) {
        DealImage(
            deal = deal,
            isFavorite = isFavorite,
            onFavoriteClicked = onFavoriteClicked,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
        )

        DealInfo(deal = deal)
    }
}

@Preview
@Composable
fun DealCardPreview() {
    SocialDealDemoTheme {
        Surface {
            DealCard(
                Deal(
                    "x6ji36jvyi4mj9fk",
                    "Bioscoopticket + popcorn + drankje bij Corendon Cinema",
                    "description",
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
                        )
                    )
                )
            )
        }
    }
}