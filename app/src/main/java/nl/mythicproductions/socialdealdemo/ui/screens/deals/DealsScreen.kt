package nl.mythicproductions.socialdealdemo.ui.screens.deals

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nl.mythicproductions.socialdealdemo.data.deal.Deal
import nl.mythicproductions.socialdealdemo.ui.kit.DealCard

@Composable
fun DealsScreen(viewModel: DealsScreenViewModel = hiltViewModel()) {
    val deals by viewModel.deals.collectAsStateWithLifecycle()

    DealsScreenLayout(deals = deals, onDealClicked = {})
}

@Composable
fun DealsScreenLayout(
    deals: List<Deal>,
    onDealClicked: (Deal) -> Unit
) {
    LazyColumn(verticalArrangement = spacedBy(16.dp), contentPadding = PaddingValues(vertical = 24.dp)) {
        items(deals) { deal ->
            DealCard(deal = deal, onClick = { onDealClicked(deal) }, modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp))
        }
    }
}