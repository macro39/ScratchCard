package com.macek.scratchcard.scratch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.macek.scratchcard.compose.components.CustomButton
import com.macek.scratchcard.compose.theme.ScratchCardTheme

@Composable
fun ScratchCardScreen() {
    val viewModel: ScratchCardViewModel = hiltViewModel()

    ScratchCardContent(
        scratchCard = viewModel::scratchCard
    )
}

@Composable
private fun ScratchCardContent(
    scratchCard: () -> Unit,
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(ScratchCardTheme.spacing.l),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("Scratch card screen")
            Spacer(modifier = Modifier.height(ScratchCardTheme.spacing.xl))
            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Scratch card",
                onClick = scratchCard
            )
        }
    }
}

@Composable
@Preview
fun ScratchCardContentPreview() {
    ScratchCardTheme {
        ScratchCardContent(
            scratchCard = {}
        )
    }
}
