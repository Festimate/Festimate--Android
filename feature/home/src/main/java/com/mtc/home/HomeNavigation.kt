package com.mtc.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mtc.navigation.Route
import kotlinx.serialization.Serializable

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateToAddMatching: () -> Unit,
) {
    composable<Home> {
        HomeRoute(
            padding = padding,
            modifier = modifier,
            navigateAddMatching = navigateToAddMatching,
        )
    }
}

@Serializable
data object Home : Route
