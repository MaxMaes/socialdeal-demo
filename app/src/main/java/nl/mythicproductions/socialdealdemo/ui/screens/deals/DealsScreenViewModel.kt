package nl.mythicproductions.socialdealdemo.ui.screens.deals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import nl.mythicproductions.socialdealdemo.data.deal.DealRepository
import javax.inject.Inject

@HiltViewModel
class DealsScreenViewModel @Inject constructor(private val dealRepository: DealRepository) :
    ViewModel() {
    private val reloadTriggerFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val deals = reloadTriggerFlow.mapLatest {
        dealRepository.getDeals()
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun reload() {
        reloadTriggerFlow.value = !reloadTriggerFlow.value
    }
}