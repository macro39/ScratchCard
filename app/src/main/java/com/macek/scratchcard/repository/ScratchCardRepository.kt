package com.macek.scratchcard.repository

import kotlinx.coroutines.flow.StateFlow

interface ScratchCardRepository {
    val scratchCardState: StateFlow<ScratchCardState>

    suspend fun scratchCard(): ScratchCardState
    suspend fun activateCard(): ActivateCardResult
}

sealed class ScratchCardState {
    data object Unscratched : ScratchCardState()

    data class Scratched(
        val code: String
    ) : ScratchCardState()

    data object Activated : ScratchCardState()
}

sealed class ActivateCardResult {
    data object Success : ActivateCardResult()
    data class Failure(val reason: String) : ActivateCardResult()
}