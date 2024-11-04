package nl.mythicproductions.socialdealdemo.ui.kit

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import nl.mythicproductions.socialdealdemo.data.deal.Currency
import nl.mythicproductions.socialdealdemo.data.deal.Deal
import nl.mythicproductions.socialdealdemo.data.deal.Price
import nl.mythicproductions.socialdealdemo.data.deal.Prices
import nl.mythicproductions.socialdealdemo.data.deal.imageUrl
import nl.mythicproductions.socialdealdemo.ui.theme.Blue
import nl.mythicproductions.socialdealdemo.ui.theme.SocialDealDemoTheme

@Composable
fun DealCard(
    deal: Deal,
    isFavorite: Boolean = false,
    onFavoriteClicked: () -> Unit = {},
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(data = deal.imageUrl)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop
    )
    val imageState by painter.state.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = spacedBy(8.dp),
        modifier = Modifier
            .clickable(onClick = onClick)
            .then(modifier)
    ) {
        Box {
            Image(
                painter = painter,
                contentDescription = deal.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(8.dp))
            )
            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .semantics {
                        role = Role.Checkbox
                        toggleableState = ToggleableState(isFavorite)
                    },
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.inverseOnSurface
                )
            }
        }

        Column(verticalArrangement = spacedBy(12.dp)) {
            Text(
                text = deal.title,
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp)
            )
            Column {
                Text(
                    text = deal.company,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = deal.city,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = deal.soldLabel,
                    style = MaterialTheme.typography.bodyLarge.copy(color = Blue)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = spacedBy(4.dp)
                ) {
                    if (deal.prices.fromPrice != null) {
                        DiscountedPriceDisplay(price = deal.prices.fromPrice)
                    }
                    CurrentPriceDisplay(price = deal.prices.price)
                }
            }
        }
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