package com.macek.scratchcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.macek.scratchcard.compose.navigation.ScratchCardNavHost
import com.macek.scratchcard.compose.theme.ScratchCardTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScratchCardTheme {
                ScratchCardNavHost()
            }
        }
    }
}