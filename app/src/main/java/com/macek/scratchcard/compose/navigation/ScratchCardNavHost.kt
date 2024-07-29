package com.macek.scratchcard.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.macek.scratchcard.activate.ActivateCardScreen
import com.macek.scratchcard.overview.CardOverviewScreen
import com.macek.scratchcard.scratch.ScratchCardScreen

@Composable
fun ScratchCardNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.CardOverview.route
    ) {
        composable(Destination.CardOverview.route) {
            CardOverviewScreen(
                scratchCard = {
                    navController.navigate(Destination.ScratchCard.route)
                },
                activateCard = {
                    navController.navigate(Destination.ActivateCard.route)
                }
            )
        }
        composable(Destination.ScratchCard.route) {
            ScratchCardScreen()
        }
        composable(Destination.ActivateCard.route) {
            ActivateCardScreen()
        }
    }
}
