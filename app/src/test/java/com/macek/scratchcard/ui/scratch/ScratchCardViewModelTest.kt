package com.macek.scratchcard.ui.scratch

import com.google.common.truth.Truth.assertThat
import com.macek.scratchcard.CoroutineDispatcherRule
import com.macek.scratchcard.repository.MockScratchCardRepository
import com.macek.scratchcard.repository.ScratchCardState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ScratchCardViewModelTest {

    @get:Rule
    val coroutineDispatcherRule = CoroutineDispatcherRule()

    private lateinit var scratchCardRepository: MockScratchCardRepository
    private lateinit var viewModel: ScratchCardViewModel

    @Before
    fun setup() {
        scratchCardRepository = MockScratchCardRepository()
        viewModel = ScratchCardViewModel(scratchCardRepository)
    }

    @Test
    fun `When scratch card state is activated Then state is correct`() = runTest {
        scratchCardRepository.scratchCardState.value = ScratchCardState.Unscratched
        val state = viewModel.state.first()
        assertThat(state.scratchCardState is ScratchCardState.Unscratched).isTrue()
        assertThat(state.isLoading).isFalse()
        assertThat(state.scratchCardButtonEnabled).isTrue()
    }

    @Test
    fun `When scratch card state is scratched Then state is correct`() = runTest {
        scratchCardRepository.scratchCardState.value = ScratchCardState.Scratched("100")
        val state = viewModel.state.first()
        assertThat(state.scratchCardState is ScratchCardState.Scratched).isTrue()
        assertThat(state.isLoading).isFalse()
        assertThat(state.scratchCardButtonEnabled).isFalse()
    }

    // I will also add some tests when clicking on scratch card button
}