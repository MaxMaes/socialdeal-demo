package nl.mythicproductions.socialdealdemo.ui.screens.dealDetail

import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.serialization.Serializable
import nl.mythicproductions.socialdealdemo.data.deal.Deal
import nl.mythicproductions.socialdealdemo.ui.UIState
import nl.mythicproductions.socialdealdemo.ui.kit.DealImage
import nl.mythicproductions.socialdealdemo.ui.kit.DealInfo

@Serializable
data class DealDetailRoute(val dealId: String)

@Composable
fun DealDetailScreen(
    navController: NavController,
    dealId: String,
    viewModel: DealDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(dealId) {
        viewModel.setDealId(dealId)
    }
    viewModel.setDealId(dealId)

    val deal by viewModel.deal.collectAsStateWithLifecycle()

    DealDetailScreenLayout(deal, onAction = { action ->
        when (action) {
            is DealDetailScreenAction.NavigateUp -> navController.navigateUp()
            is DealDetailScreenAction.FavoriteDeal -> viewModel.favoriteDeal(action.dealId)
            is DealDetailScreenAction.UnfavoriteDeal -> viewModel.unfavoriteDeal(action.dealId)
        }
    })
}

@Composable
fun DealDetailScreenLayout(deal: UIState<Deal?>, onAction: (DealDetailScreenAction) -> Unit) {
    Scaffold(
        topBar = {
            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = { onAction(DealDetailScreenAction.NavigateUp) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        contentWindowInsets = WindowInsets.navigationBars
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())) {
            if (deal is UIState.Success) {
                val dealData = deal.data
                if (dealData == null) {
                    // Show error
                } else {
                    // Show deal data
                    Column(modifier = Modifier.padding()) {
                        DealImage(
                            deal = dealData,
                            isFavorite = dealData.isFavorite,
                            onFavoriteClicked = {
                                if (dealData.isFavorite) {
                                    onAction(
                                        DealDetailScreenAction.UnfavoriteDeal(
                                            dealData.unique
                                        )
                                    )
                                } else {
                                    onAction(DealDetailScreenAction.FavoriteDeal(dealData.unique))
                                }
                            })
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = spacedBy(16.dp)
                        ) {
                            DealInfo(deal = dealData)
                            // TODO: Parse HTML description to AnnotatedString so we can use it in Text instead of AndroidView
//                Text(dealData.description, style = MaterialTheme.typography.bodyMedium)


                            val textColor = MaterialTheme.colorScheme.onSurface
                            AndroidView(
                                factory = { context -> TextView(context) },
                                update = {
                                    it.text = HtmlCompat.fromHtml(
                                        dealData.description,
                                        HtmlCompat.FROM_HTML_MODE_COMPACT
                                    )
                                    it.setTextColor(textColor.toArgb())
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed class DealDetailScreenAction {
    data object NavigateUp : DealDetailScreenAction()
    data class FavoriteDeal(val dealId: String) : DealDetailScreenAction()
    data class UnfavoriteDeal(val dealId: String) : DealDetailScreenAction()
}