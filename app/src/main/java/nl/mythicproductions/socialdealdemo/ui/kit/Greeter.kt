package nl.mythicproductions.socialdealdemo.ui.kit

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import nl.mythicproductions.socialdealdemo.R

@Composable
fun Greeter() {
    val currentTime = remember { Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()) }
    val greeting = when (currentTime.hour) {
        in 6..11 -> stringResource(R.string.greeting_morning)
        in 12..17 -> stringResource(R.string.greeting_afternoon)
        in 18..23 -> stringResource(R.string.greeting_evening)
        else -> stringResource(R.string.greeting_night)
    }
    ScreenHeader(text = greeting)
}