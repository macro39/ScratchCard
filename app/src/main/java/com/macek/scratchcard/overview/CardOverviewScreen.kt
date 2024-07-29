package com.macek.scratchcard.overview

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.macek.scratchcard.compose.components.CustomButton
import com.macek.scratchcard.compose.theme.ScratchCardTheme

@Composable
fun CardOverviewScreen(
    scratchCard: () -> Unit,
    activateCard: () -> Unit,
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
            Text(text = "Card overview screen", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(ScratchCardTheme.spacing.xl))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(ScratchCardTheme.spacing.s)
            ) {
                CustomButton(
                    modifier = Modifier.weight(1f),
                    text = "Scratch card",
                    onClick = scratchCard
                )
                CustomButton(
                    modifier = Modifier.weight(1f),
                    text = "Activate card",
                    onClick = activateCard
                )
            }
        }
    }
}

@Composable
@Preview
fun CardOverviewScreenPreview() {
    ScratchCardTheme {
        CardOverviewScreen(
            scratchCard = {},
            activateCard = {},
        )
    }
}
