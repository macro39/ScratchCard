package com.macek.scratchcard.ui.scratch

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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScratchCardViewModel @Inject constructor(
    private val scratchCardRepository: ScratchCardRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ScratchCardUiState())
    val state: StateFlow<ScratchCardUiState> = _state

    init {
        scratchCardRepository.scratchCardState.onEach { scratchCardState ->
            _state.update {
                it.copy(
                    scratchCardState = scratchCardState,
                    scratchCardEnabled = scratchCardState is ScratchCardState.Unscratched
                )
            }
        }.launchIn(viewModelScope)
    }

    fun scratchCard() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            scratchCardRepository.scratchCard()
            _state.update {
                it.copy(isLoading = false)
            }
        }
    }

    data class ScratchCardUiState(
        val scratchCardState: ScratchCardState = ScratchCardState.Unscratched,
        val isLoading: Boolean = false,
        val scratchCardEnabled: Boolean = false
    )
}