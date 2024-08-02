package com.macek.scratchcard.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID

class MockScratchCardRepository : ScratchCardRepository {

    val testingScratchCardState = MutableStateFlow<ScratchCardState>(ScratchCardState.Unscratched)

    override val scratchCardState = testingScratchCardState

    override suspend fun scratchCard() {
        if (scratchCardState.value == ScratchCardState.Unscratched) {
            delay(2000)
            testingScratchCardState.value = ScratchCardState.Scratched(UUID.randomUUID().toString())
        }
    }

    override suspend fun activateCard(): ActivateCardResult {
        val currentState = testingScratchCardState.value
        return if (currentState is ScratchCardState.Scratched) {
            delay(2000)
            if (currentState.code != "100") {
                testingScratchCardState.value = ScratchCardState.Activated
                ActivateCardResult.Success
            } else {
                ActivateCardResult.Failure("Error occurred")
            }
        } else {
            ActivateCardResult.Failure("Error occurred")
        }
    }
}