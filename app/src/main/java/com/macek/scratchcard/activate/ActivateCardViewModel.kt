package com.macek.scratchcard.activate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macek.scratchcard.repository.ActivateCardResult
import com.macek.scratchcard.repository.ScratchCardRepository
import com.macek.scratchcard.repository.ScratchCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivateCardViewModel @Inject constructor(
    private val scratchCardRepository: ScratchCardRepository,
) : ViewModel() {

    val uiState = MutableStateFlow(UiState())

    fun activateCard() {
        CoroutineScope(Dispatchers.Default).launch {
            uiState.update {
                it.copy(isLoading = true)
            }

            val result = scratchCardRepository.activateCard()
            val errorDialogText = when (result) {
                is ActivateCardResult.Failure -> result.reason
                ActivateCardResult.Success -> null
            }
            uiState.update {
                it.copy(
                    isLoading = false,
                    errorDialogText = errorDialogText
                )
            }
        }
    }

    fun dismissErrorDialog() {
        uiState.update {
            it.copy(errorDialogText = null)
        }
    }

    init {
        scratchCardRepository.scratchCardState.onEach { scratchCardState ->
            uiState.update {
                it.copy(
                    scratchCardState = scratchCardState,
                    activateCardEnabled = scratchCardState is ScratchCardState.Scratched
                )
            }
        }.launchIn(viewModelScope)
    }

    data class UiState(
        val scratchCardState: ScratchCardState = ScratchCardState.Unscratched,
        val activateCardEnabled: Boolean = true,
        val isLoading: Boolean = false,
        val errorDialogText: String? = null,
    )
}