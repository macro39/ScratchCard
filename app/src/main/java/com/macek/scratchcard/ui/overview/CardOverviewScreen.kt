package com.macek.scratchcard.ui.overview

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.macek.scratchcard.repository.ScratchCardState
import com.macek.scratchcard.ui.components.CustomButton
import com.macek.scratchcard.ui.components.ScratchCard
import com.macek.scratchcard.ui.theme.ScratchCardTheme

@Composable
fun CardOverviewScreen(
    scratchCard: () -> Unit,
    activateCard: () -> Unit,
) {
    val viewModel: CardOverviewViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    CardOverviewContent(
        state = state,
        scratchCard = scratchCard,
        activateCard = activateCard
    )
}

@Composable
private fun CardOverviewContent(
    state: CardOverviewViewModel.CardOverviewUiState,
    scratchCard: () -> Unit,
    activateCard: () -> Unit,
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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
                    enabled = state.scratchCardButtonEnabled,
                    onClick = scratchCard
                )
                CustomButton(
                    modifier = Modifier.weight(1f),
                    text = "Activate card",
                    enabled = state.activateCardButtonEnabled,
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
            state = CardOverviewViewModel.CardOverviewUiState(ScratchCardState.Unscratched),
            scratchCard = {},
            activateCard = {},
        )
    }
}
