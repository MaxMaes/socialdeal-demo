package nl.mythicproductions.socialdealdemo.ui.kit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nl.mythicproductions.socialdealdemo.data.deal.Deal
import nl.mythicproductions.socialdealdemo.ui.theme.Blue
import nl.mythicproductions.socialdealdemo.ui.theme.GrayMedium

@Composable
fun DealInfo(deal: Deal) {
    Column(verticalArrangement = spacedBy(12.dp)) {
        Text(
            text = deal.title,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp)
        )
        Column {
            Text(
                text = deal.company,
                style = MaterialTheme.typography.bodyLarge.copy(color = GrayMedium)
            )
            Text(
                text = deal.city,
                style = MaterialTheme.typography.bodyLarge.copy(color = GrayMedium)
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