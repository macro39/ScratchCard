package com.macek.scratchcard.activate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.macek.scratchcard.compose.components.CustomButton
import com.macek.scratchcard.compose.components.ErrorDialog
import com.macek.scratchcard.compose.components.LoadingOverlay
import com.macek.scratchcard.compose.theme.ScratchCardTheme
import com.macek.scratchcard.repository.ScratchCardState

@Composable
fun ActivateCardScreen() {
    val viewModel: ActivateCardViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ActivateCardContent(
        state = state,
        activateCard = viewModel::activateCard,
        dismissErrorDialog = viewModel::dismissErrorDialog
    )
}

@Composable
private fun ActivateCardContent(
    state: ActivateCardViewModel.UiState,
    activateCard: () -> Unit,
    dismissErrorDialog: () -> Unit,
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
                Text(state.scratchCardState.getTitle())
                Spacer(modifier = Modifier.height(ScratchCardTheme.spacing.xl))
                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Activate card",
                    enabled = state.activateCardEnabled,
                    onClick = activateCard
                )
            }
            if (state.isLoading) {
                LoadingOverlay("Activating card...")
            }
            if (state.errorDialogText != null) {
                ErrorDialog(
                    text = state.errorDialogText,
                    onDismiss = dismissErrorDialog
                )
            }
        }
    }
}

@Composable
private fun ScratchCardState.getTitle() =
    when (this) {
        ScratchCardState.Activated -> "Scratch card is already activated"
        is ScratchCardState.Scratched -> "Activate scratch card"
        ScratchCardState.Unscratched -> "Cannot activate unscratched card"
    }

@Composable
@Preview
private fun ActivateCardContentPreview() {
    ScratchCardTheme {
        ActivateCardContent(
            state = ActivateCardViewModel.UiState(
                activateCardEnabled = true,
                isLoading = false,
            ),
            activateCard = {},
            dismissErrorDialog = {},
        )
    }
}