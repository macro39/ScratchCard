package com.macek.scratchcard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.macek.scratchcard.ui.theme.ScratchCardTheme

@Composable
fun ErrorDialog(
    text: String,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Column(
            modifier = Modifier
                .padding(ScratchCardTheme.spacing.xl)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(ScratchCardTheme.spacing.l))
                .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(ScratchCardTheme.spacing.l))
                .padding(ScratchCardTheme.spacing.xl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(ScratchCardTheme.spacing.l)
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null
            )
            Text(text = text)
            CustomButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Close",
                enabled = true,
                onClick = onDismiss
            )
        }
    }
}