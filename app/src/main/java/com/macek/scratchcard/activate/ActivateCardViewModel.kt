package com.macek.scratchcard.activate

import androidx.lifecycle.ViewModel
import com.macek.scratchcard.repository.ScratchCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivateCardViewModel @Inject constructor(
    private val scratchCardRepository: ScratchCardRepository,
) : ViewModel() {

    fun activateCard() {
        scratchCardRepository.activateCard()
    }
}