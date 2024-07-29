package com.macek.scratchcard.scratch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macek.scratchcard.repository.ScratchCardRepository
import com.macek.scratchcard.repository.ScratchCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScratchCardViewModel @Inject constructor(
    private val scratchCardRepository: ScratchCardRepository,
) : ViewModel() {

    val uiState = MutableStateFlow(UiState())

    init {
        scratchCardRepository.scratchCardState.onEach {
            uiState.value = UiState(scratchCardState = it, scratchCardEnabled = it is ScratchCardState.Unscratched)
        }.launchIn(viewModelScope)
    }

    fun scratchCard() {
        viewModelScope.launch {
            uiState.update {
                it.copy(isLoading = true)
            }
            scratchCardRepository.scratchCard()
            uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    data class UiState(
        val scratchCardState: ScratchCardState = ScratchCardState.Unscratched,
        val isLoading: Boolean = false,
        val scratchCardEnabled: Boolean = false
    )
}