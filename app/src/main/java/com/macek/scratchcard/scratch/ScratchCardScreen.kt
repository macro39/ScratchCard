package com.macek.scratchcard.scratch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.macek.scratchcard.compose.components.CustomButton
import com.macek.scratchcard.compose.components.LoadingOverlay
import com.macek.scratchcard.compose.components.ScratchCard
import com.macek.scratchcard.compose.theme.ScratchCardTheme
import com.macek.scratchcard.repository.ScratchCardState

@Composable
fun ScratchCardScreen() {
    val viewModel: ScratchCardViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ScratchCardContent(
        state = state,
        scratchCard = viewModel::scratchCard
    )
}

@Composable
private fun ScratchCardContent(
    state: ScratchCardViewModel.UiState,
    scratchCard: () -> Unit,
) {
    Scaffold {
        Box {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(ScratchCardTheme.spacing.l),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                ScratchCard(scratchCardState = state.scratchCardState)
                Spacer(modifier = Modifier.height(ScratchCardTheme.spacing.xl))
                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Scratch card",
                    enabled = state.scratchCardEnabled,
                    onClick = scratchCard
                )
            }
            if (state.isLoading) {
                LoadingOverlay("Scratching card...")
            }
        }
    }
}

@Composable
@Preview
private fun ScratchCardContentPreview() {
    ScratchCardTheme {
        ScratchCardContent(
            state = ScratchCardViewModel.UiState(
                scratchCardState = ScratchCardState.Unscratched,
                isLoading = false,
            ),
            scratchCard = {}
        )
    }
}
