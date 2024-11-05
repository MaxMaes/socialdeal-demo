package nl.mythicproductions.socialdealdemo.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import nl.mythicproductions.socialdealdemo.data.deal.DealRepository
import nl.mythicproductions.socialdealdemo.data.favorites.FavoriteRepository
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val dealRepository: DealRepository,
    private val favoriteRepository: FavoriteRepository
) :
    ViewModel() {
    private val reloadTriggerFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val favorites = favoriteRepository.getFavoritesFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dealsData = reloadTriggerFlow.mapLatest {
        dealRepository.getDeals()
    }

    val deals = combine(dealsData, favorites) { deals, favorites ->
        deals.filter { favorites.contains(it.unique) }.map { deal ->
            deal.copy(isFavorite = true)
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun reload() {
        reloadTriggerFlow.value = !reloadTriggerFlow.value
    }

    fun favoriteDeal(dealId: String) {
        favoriteRepository.addFavorite(dealId)
    }

    fun unfavoriteDeal(dealId: String) {
        favoriteRepository.removeFavorite(dealId)
    }
}