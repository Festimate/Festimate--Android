package com.mtc.idealtype

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mtc.navigation.Route
import kotlinx.serialization.Serializable

fun NavController.navigateIdealType(navOptions: NavOptions) {
    navigate(IdealType, navOptions)
}

fun NavGraphBuilder.idealTypeNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateToAddMatching: () -> Unit,
) {
    composable<IdealType> {
        IdealTypeRoute(
            padding = padding,
            modifier = modifier,
            navigateAddMatching = navigateToAddMatching,
        )
    }
}

@Serializable
data object IdealType : Route
