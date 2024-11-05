package nl.mythicproductions.socialdealdemo.ui.screens.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import nl.mythicproductions.socialdealdemo.R
import nl.mythicproductions.socialdealdemo.data.deal.Deal
import nl.mythicproductions.socialdealdemo.ui.kit.DealCard
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
                Column(
                    modifier = Modifier.padding(top = 24.dp),
                    verticalArrangement = spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_socialdeal_logo),
                        contentDescription = "Empty state",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .width(150.dp)
                    )

                    Text(
                        text = "Je hebt nog geen favorieten! Ga naar de deals tab en voeg een deal toe aan je favorieten.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
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