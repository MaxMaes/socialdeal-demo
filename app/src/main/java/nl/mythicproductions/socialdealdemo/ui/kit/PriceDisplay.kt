package nl.mythicproductions.socialdealdemo.ui.kit

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import nl.mythicproductions.socialdealdemo.data.deal.Price
import nl.mythicproductions.socialdealdemo.ui.navigation.LocalCurrencyIndicator
import nl.mythicproductions.socialdealdemo.ui.theme.GrayMedium
import nl.mythicproductions.socialdealdemo.ui.theme.Green
import nl.mythicproductions.socialdealdemo.ui.theme.PriceFont
import java.util.Locale

@Composable
fun CurrentPriceDisplay(price: Price, modifier: Modifier = Modifier) {
    PriceText(price, modifier, PriceFont.copy(color = Green))
}

@Composable
fun DiscountedPriceDisplay(price: Price, modifier: Modifier = Modifier) {
    PriceText(
        price,
        modifier,
        PriceFont.copy(
            color = GrayMedium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            textDecoration = TextDecoration.LineThrough
        )
    )
}

@Composable
private fun PriceText(
    price: Price,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = PriceFont
) {
    val formatted = String.format(Locale.getDefault(), "%.2f", price.amount / 100.0)

    Text(
        text = "${LocalCurrencyIndicator.current} $formatted",
        style = textStyle
    )
}