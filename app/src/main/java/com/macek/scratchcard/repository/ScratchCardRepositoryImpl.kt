package com.macek.scratchcard.repository

import android.util.Log
import com.macek.scratchcard.data.remote.CardApi
import com.macek.scratchcard.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import java.util.UUID

private const val TAG = "ScratchCardRepository"

class ScratchCardRepositoryImpl(
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val cardApi: CardApi,
) : ScratchCardRepository {

    override val scratchCardState = MutableStateFlow<ScratchCardState>(ScratchCardState.Unscratched)

    override suspend fun scratchCard(): ScratchCardState {
        delay(2000)
        scratchCardState.value = ScratchCardState.Scratched(UUID.randomUUID().toString())
        return scratchCardState.value
    }

    override suspend fun activateCard(): ActivateCardResult = withContext(applicationScope.coroutineContext + NonCancellable) {
        val currentScratchCardState = scratchCardState.value
        return@withContext if (currentScratchCardState is ScratchCardState.Scratched) {
            try {
                val response = cardApi.activate(currentScratchCardState.code)
                if (response.isSuccessful) {
                    response.body()?.android?.let { android ->
                        if (android > 277028) {
                            scratchCardState.value = ScratchCardState.Activated
                            ActivateCardResult.Success
                        } else {
                            ActivateCardResult.Failure("Activation did not succeeded")
                        }
                    } ?: ActivateCardResult.Failure("Failed to activate scratch card")
                } else {
                    ActivateCardResult.Failure("Failed to activate scratch card - error code ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception occurred $e")
                val errorMessage = if (e is UnknownHostException) {
                    "No internet connection"
                } else {
                    e.message ?: "An error occurred when activating scratch card"
                }
                ActivateCardResult.Failure(errorMessage)
            }
        } else {
            ActivateCardResult.Failure("Scratch card is not in valid state to activate")
        }
    }
}