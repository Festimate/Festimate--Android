package com.mtc.addmatching

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mtc.navigation.Route
import kotlinx.serialization.Serializable

fun NavController.navigateAddMatching(navOptions: NavOptions) {
    navigate(AddMatching, navOptions)
}

fun NavGraphBuilder.addMatchingGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
) {
    composable<AddMatching> {
        AddMatchingRoute(
            padding = padding,
            modifier = modifier,
            navigateHome = navigateToHome,
        )
    }
}

@Serializable
data object AddMatching : Route
