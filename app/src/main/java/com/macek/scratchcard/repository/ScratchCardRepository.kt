package com.macek.scratchcard.repository

import kotlinx.coroutines.flow.StateFlow

interface ScratchCardRepository {
    val scratchCardState: StateFlow<ScratchCardState>

    suspend fun scratchCard(): ScratchCardState
    fun activateCard()
}

sealed class ScratchCardState {
    data object Unscratched : ScratchCardState()

    data class Scratched(
        val code: String
    ) : ScratchCardState()

    data object Activated : ScratchCardState()
}