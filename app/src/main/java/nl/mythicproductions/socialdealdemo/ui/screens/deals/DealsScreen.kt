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
import androidx.navigation.NavController
import nl.mythicproductions.socialdealdemo.data.deal.Deal
import nl.mythicproductions.socialdealdemo.ui.kit.DealCard
import nl.mythicproductions.socialdealdemo.ui.screens.dealDetail.DealDetailRoute

@Composable
fun DealsScreen(navController: NavController, viewModel: DealsScreenViewModel = hiltViewModel()) {
    val deals by viewModel.deals.collectAsStateWithLifecycle()

    DealsScreenLayout(
        deals = deals,
        onAction = { action ->
        when (action) {
            is DealScreenActions.NavigateToDealDetail -> {
                navController.navigate(DealDetailRoute(dealId = action.dealId))
            }
            is DealScreenActions.FavoriteDeal -> viewModel.favoriteDeal(action.dealId)
            is DealScreenActions.UnfavoriteDeal -> viewModel.unfavoriteDeal(action.dealId)
        }
    })
}

@Composable
fun DealsScreenLayout(
    deals: List<Deal>,
    onAction: (DealScreenActions) -> Unit
) {
    LazyColumn(
        verticalArrangement = spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 24.dp)
    ) {
        items(deals) { deal ->
            DealCard(
                deal = deal,
                onClick = { onAction(DealScreenActions.NavigateToDealDetail(deal.unique)) },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                onFavoriteClicked = {
                    onAction(
                        if (deal.isFavorite) {
                            DealScreenActions.UnfavoriteDeal(deal.unique)
                        } else {
                            DealScreenActions.FavoriteDeal(deal.unique)
                        }
                    )
                }
            )
        }
    }
}

sealed class DealScreenActions {
    data class NavigateToDealDetail(val dealId: String) : DealScreenActions()
    data class FavoriteDeal(val dealId: String) : DealScreenActions()
    data class UnfavoriteDeal(val dealId: String) : DealScreenActions()
}