package com.macek.scratchcard.overview

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.macek.scratchcard.compose.components.CustomButton
import com.macek.scratchcard.compose.components.ScratchCard
import com.macek.scratchcard.compose.theme.ScratchCardTheme
import com.macek.scratchcard.repository.ScratchCardState

@Composable
fun CardOverviewScreen(
    scratchCard: () -> Unit,
    activateCard: () -> Unit,
) {
    val viewModel: CardOverviewViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    CardOverviewContent(
        state = state,
        scratchCard = scratchCard,
        activateCard = activateCard
    )
}

@Composable
private fun CardOverviewContent(
    state: CardOverviewViewModel.UiState,
    scratchCard: () -> Unit,
    activateCard: () -> Unit,
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(ScratchCardTheme.spacing.l)
                .animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ScratchCard(state.scratchCardState)
            Spacer(modifier = Modifier.height(ScratchCardTheme.spacing.xl))
            Text(
                text = state.scratchCardState.toText(),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(ScratchCardTheme.spacing.xl))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(ScratchCardTheme.spacing.s)
            ) {
                CustomButton(
                    modifier = Modifier.weight(1f),
                    text = "Scratch card",
                    enabled = state.scratchEnabled,
                    onClick = scratchCard
                )
                CustomButton(
                    modifier = Modifier.weight(1f),
                    text = "Activate card",
                    enabled = state.activateEnabled,
                    onClick = activateCard
                )
            }
        }
    }
}

@Composable
private fun ScratchCardState.toText(): String =
    when (this) {
        ScratchCardState.Activated -> "Scratch card is activated"
        is ScratchCardState.Scratched -> "Scratch card is scratched - code ${this.code}"
        ScratchCardState.Unscratched -> "Scratch card is unscratched"
    }

@Composable
@Preview
private fun CardOverviewContentPreview() {
    ScratchCardTheme {
        CardOverviewContent(
            state = CardOverviewViewModel.UiState(ScratchCardState.Unscratched),
            scratchCard = {},
            activateCard = {},
        )
    }
}
