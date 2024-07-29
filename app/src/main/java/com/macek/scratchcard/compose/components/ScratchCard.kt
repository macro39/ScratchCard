package com.macek.scratchcard.compose.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.macek.scratchcard.compose.theme.ScratchCardTheme
import com.macek.scratchcard.repository.ScratchCardState

@Composable
fun ScratchCard(
    scratchCardState: ScratchCardState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(ScratchCardTheme.spacing.xl))
            .clip(RoundedCornerShape(ScratchCardTheme.spacing.xl))
            .padding(ScratchCardTheme.spacing.xl),
    ) {
        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            text = "Scratch card"
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(
                    MaterialTheme.colorScheme.secondary,
                    RoundedCornerShape(ScratchCardTheme.spacing.l)
                )
                .fillMaxWidth(0.7f)
                .aspectRatio(3f)
                .padding(ScratchCardTheme.spacing.s),
            contentAlignment = Alignment.Center
        ) {
            val text = when (scratchCardState) {
                ScratchCardState.Activated -> "Already activated"
                is ScratchCardState.Scratched -> scratchCardState.code
                ScratchCardState.Unscratched -> "Scratch here"
            }
            Text(
                text = text,
                textAlign = TextAlign.Center
            )
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomEnd),
            visible = scratchCardState is ScratchCardState.Unscratched,
            enter = expandHorizontally(),
            exit = shrinkHorizontally(
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = LinearOutSlowInEasing
                )
            ),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .background(
                        MaterialTheme.colorScheme.inverseOnSurface,
                        RoundedCornerShape(ScratchCardTheme.spacing.l)
                    )
                    .fillMaxWidth(0.7f)
                    .aspectRatio(3f)
                    .padding(ScratchCardTheme.spacing.s),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Scratch to show code",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}