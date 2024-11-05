package nl.mythicproductions.socialdealdemo.ui.screens.favorites

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import nl.mythicproductions.socialdealdemo.R
import nl.mythicproductions.socialdealdemo.data.deal.Deal
import nl.mythicproductions.socialdealdemo.ui.kit.DealCard
import nl.mythicproductions.socialdealdemo.ui.kit.EmptyState
import nl.mythicproductions.socialdealdemo.ui.screens.dealDetail.DealDetailRoute

@Composable
fun FavoritesScreen(navController: NavController, viewModel: FavoritesViewModel = hiltViewModel()) {
    val deals by viewModel.deals.collectAsStateWithLifecycle()

    FavoritesScreenLayout(
        deals = deals,
        onAction = { action ->
            when (action) {
                is FavoritesScreenActions.NavigateToDealDetail -> {
                    navController.navigate(DealDetailRoute(dealId = action.dealId))
                }

                is FavoritesScreenActions.FavoriteDeal -> viewModel.favoriteDeal(action.dealId)
                is FavoritesScreenActions.UnfavoriteDeal -> viewModel.unfavoriteDeal(action.dealId)
            }
        })
}

@Composable
fun FavoritesScreenLayout(
    deals: List<Deal>,
    onAction: (FavoritesScreenActions) -> Unit
) {
    LazyColumn(
        verticalArrangement = spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 24.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        if (deals.isEmpty()) {
            item {
                EmptyState(
                    title = stringResource(R.string.favorites_empty_state_title),
                    description = stringResource(R.string.favorites_empty_state_description),
                    modifier = Modifier.fillParentMaxHeight()
                )
            }
        }
        items(deals, key = { deal -> deal.unique }) { deal ->
            DealCard(
                deal = deal,
                onClick = { onAction(FavoritesScreenActions.NavigateToDealDetail(deal.unique)) },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .animateItem(),
                onFavoriteClicked = {
                    onAction(
                        if (deal.isFavorite) {
                            FavoritesScreenActions.UnfavoriteDeal(deal.unique)
                        } else {
                            FavoritesScreenActions.FavoriteDeal(deal.unique)
                        }
                    )
                }
            )
        }
    }
}

sealed class FavoritesScreenActions {
    data class NavigateToDealDetail(val dealId: String) : FavoritesScreenActions()
    data class FavoriteDeal(val dealId: String) : FavoritesScreenActions()
    data class UnfavoriteDeal(val dealId: String) : FavoritesScreenActions()
}