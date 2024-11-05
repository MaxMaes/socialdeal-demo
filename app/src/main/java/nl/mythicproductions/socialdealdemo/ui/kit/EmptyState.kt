package nl.mythicproductions.socialdealdemo.ui.kit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import nl.mythicproductions.socialdealdemo.R

@Composable
fun EmptyState(
    icon: ImageVector = ImageVector.vectorResource(id = R.drawable.emptystate),
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = 24.dp)
            .fillMaxWidth(),
        verticalArrangement = spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = icon,
            contentDescription = "Empty state",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .width(150.dp)
        )

        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.W700)
        )

        Text(
            text = description,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}