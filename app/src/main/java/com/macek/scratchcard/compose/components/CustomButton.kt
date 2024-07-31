package com.macek.scratchcard.compose.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.macek.scratchcard.compose.theme.ScratchCardTheme

@Composable
fun CustomButton(
    modifier: Modifier,
    text: String,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        contentPadding = PaddingValues(ScratchCardTheme.spacing.l),
        enabled = enabled,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
@Preview
private fun CustomButtonPreview() {
    ScratchCardTheme {
        CustomButton(
            modifier = Modifier,
            text = "Custom button",
            enabled = true,
            onClick = {},
        )
    }
}