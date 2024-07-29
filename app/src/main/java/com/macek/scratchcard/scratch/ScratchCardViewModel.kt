package com.macek.scratchcard.scratch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.macek.scratchcard.repository.ScratchCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScratchCardViewModel @Inject constructor(
    private val scratchCardRepository: ScratchCardRepository,
) : ViewModel() {

    fun scratchCard() {
        viewModelScope.launch {
            scratchCardRepository.scratchCard()
        }
    }
}