package com.macek.scratchcard.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.macek.scratchcard.repository.ScratchCardState
import com.macek.scratchcard.ui.theme.ScratchCardTheme
import com.macek.scratchcard.ui.theme.isLandscape

@Composable
fun ScratchCard(
    scratchCardState: ScratchCardState
) {
    val cardWidth = if (isLandscape()) {
        300.dp
    } else {
        LocalConfiguration.current.screenWidthDp.dp
    }
    Box(
        modifier = Modifier
            .width(cardWidth)
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
            enter = fadeIn(),
            exit = fadeOut(
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