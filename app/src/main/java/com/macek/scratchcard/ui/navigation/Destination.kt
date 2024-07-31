package com.macek.scratchcard.ui.navigation

sealed class Destination(
    val route: String
) {
    data object CardOverview : Destination("card_overview")
    data object ScratchCard : Destination("scratch_card")
    data object ActivateCard : Destination("activate_card")
}
