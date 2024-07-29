package com.macek.scratchcard.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class ScratchCardRepositoryImpl(
    @ApplicationScope private val coroutineScope: CoroutineScope
) : ScratchCardRepository {

    override val scratchCardState = MutableStateFlow<ScratchCardState>(ScratchCardState.Unscratched)

    override suspend fun scratchCard(): ScratchCardState {
        // TODO implement logic
        delay(2000)
        scratchCardState.value = ScratchCardState.Scratched(UUID.randomUUID().toString())
        return scratchCardState.value
    }

    override fun activateCard() {
        coroutineScope.launch {
            // TODO implement logic
            delay(2000)
            scratchCardState.value = ScratchCardState.Activated
        }
    }
}