package nl.mythicproductions.socialdealdemo.ui.kit

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ScreenHeader(text: String) {
    Text(text = text, style = MaterialTheme.typography.headlineLarge)
}