package nl.mythicproductions.socialdealdemo.ui.screens.dealDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import nl.mythicproductions.socialdealdemo.data.deal.DealRepository
import nl.mythicproductions.socialdealdemo.ui.UIState
import javax.inject.Inject

@HiltViewModel
class DealDetailViewModel @Inject constructor(private val dealRepository: DealRepository) :
    ViewModel() {
    private val reloadTriggerFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val dealId = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val deal = combine(dealId, reloadTriggerFlow) { id, _ -> id }
        .filterNotNull()
        .mapLatest { id ->
            // Load deal by id
            val result = dealRepository.getDealById(id)
            UIState.Success(result)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UIState.Idle
        )

    fun setDealId(id: String) {
        dealId.update { id }
    }
}