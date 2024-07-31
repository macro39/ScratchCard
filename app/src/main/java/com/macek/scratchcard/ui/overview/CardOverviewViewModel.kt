package com.macek.scratchcard.ui.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macek.scratchcard.repository.ScratchCardRepository
import com.macek.scratchcard.repository.ScratchCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CardOverviewViewModel @Inject constructor(
    scratchCardRepository: ScratchCardRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CardOverviewUiState())
    val uiState: StateFlow<CardOverviewUiState> = _uiState

    init {
        scratchCardRepository.scratchCardState.onEach { scratchCardState ->
            _uiState.update {
                CardOverviewUiState(
                    scratchCardState = scratchCardState,
                    scratchEnabled = scratchCardState is ScratchCardState.Unscratched,
                    activateEnabled = scratchCardState is ScratchCardState.Scratched
                )
            }
        }.launchIn(viewModelScope)
    }

    class CardOverviewUiState(
        val scratchCardState: ScratchCardState = ScratchCardState.Unscratched,
        val scratchEnabled: Boolean = true,
        val activateEnabled: Boolean = false,
    )
}
