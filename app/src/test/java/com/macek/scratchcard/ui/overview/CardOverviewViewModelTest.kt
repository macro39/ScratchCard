package com.macek.scratchcard.ui.overview

import com.google.common.truth.Truth.assertThat
import com.macek.scratchcard.CoroutineDispatcherRule
import com.macek.scratchcard.repository.MockScratchCardRepository
import com.macek.scratchcard.repository.ScratchCardState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardOverviewViewModelTest {

    @get:Rule
    val coroutineDispatcherRule = CoroutineDispatcherRule()

    private lateinit var scratchCardRepository: MockScratchCardRepository
    private lateinit var viewModel: CardOverviewViewModel

    @Before
    fun setup() {
        scratchCardRepository = MockScratchCardRepository()
        viewModel = CardOverviewViewModel(scratchCardRepository)
    }

    @Test
    fun `When scratch card state is unscratched Then state is correct`() = runTest {
        scratchCardRepository.testingScratchCardState.value = ScratchCardState.Unscratched
        val state = viewModel.state.first()
        assertThat(state.scratchCardState is ScratchCardState.Unscratched).isTrue()
        assertThat(state.scratchCardButtonEnabled).isTrue()
        assertThat(state.activateCardButtonEnabled).isFalse()
    }

    @Test
    fun `When scratch card state is scratched Then state is correct`() = runTest {
        scratchCardRepository.scratchCardState.value = ScratchCardState.Scratched("100")
        val state = viewModel.state.first()
        assertThat(state.scratchCardState is ScratchCardState.Scratched).isTrue()
        assertThat(state.scratchCardButtonEnabled).isFalse()
        assertThat(state.activateCardButtonEnabled).isTrue()
    }

    @Test
    fun `When scratch card state is activated Then state is correct`() = runTest {
        scratchCardRepository.scratchCardState.value = ScratchCardState.Activated
        val state = viewModel.state.first()
        assertThat(state.scratchCardState is ScratchCardState.Activated).isTrue()
        assertThat(state.scratchCardButtonEnabled).isFalse()
        assertThat(state.activateCardButtonEnabled).isFalse()
    }
}