package nl.mythicproductions.socialdealdemo.ui.screens.dealDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.serialization.Serializable
import nl.mythicproductions.socialdealdemo.data.deal.Deal
import nl.mythicproductions.socialdealdemo.ui.UIState
import nl.mythicproductions.socialdealdemo.ui.kit.DealCard

@Serializable
data class DealDetailRoute(val dealId: String)

@Composable
fun DealDetailScreen(dealId: String, viewModel: DealDetailViewModel = hiltViewModel()) {
    LaunchedEffect(dealId) {
        viewModel.setDealId(dealId)
    }
    viewModel.setDealId(dealId)

    val deal by viewModel.deal.collectAsStateWithLifecycle()

    DealDetailScreenLayout(deal)
}

@Composable
fun DealDetailScreenLayout(deal: UIState<Deal?>) {
    if (deal is UIState.Success) {
        val dealData = deal.data
        if (dealData == null) {
            // Show error
        } else {
            // Show deal data
            Column {
                DealCard(dealData)
                Text(dealData.description, style = MaterialTheme.typography.bodyMedium)
            }

        }
    }
}