package com.macek.scratchcard.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macek.scratchcard.repository.ScratchCardRepository
import com.macek.scratchcard.repository.ScratchCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CardOverviewViewModel @Inject constructor(
    scratchCardRepository: ScratchCardRepository
) : ViewModel() {

    val uiState = MutableStateFlow(UiState())

    init {
        scratchCardRepository.scratchCardState.onEach { scratchCardState ->
            uiState.tryEmit(
                UiState(
                    scratchCardState = scratchCardState,
                    scratchEnabled = scratchCardState is ScratchCardState.Unscratched,
                    activateEnabled = scratchCardState is ScratchCardState.Scratched
                )
            )
        }.launchIn(viewModelScope)
    }

    class UiState(
        val scratchCardState: ScratchCardState = ScratchCardState.Unscratched,
        val scratchEnabled: Boolean = true,
        val activateEnabled: Boolean = false,
    )
}
