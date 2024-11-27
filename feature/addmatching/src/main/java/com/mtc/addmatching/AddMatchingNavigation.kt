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
    navigateToIdealType: () -> Unit,
    navigateToDateTaste: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToBack: () -> Unit,
    getDateTasteSavedStateHandle: () -> List<Int>?,
    getIdealTypeSavedStateHandle: () -> String?,
) {
    composable<AddMatching> {
        AddMatchingRoute(
            padding = padding,
            modifier = modifier,
            navigateIdealType = navigateToIdealType,
            navigateDateTaste = navigateToDateTaste,
            navigateHome = navigateToHome,
            navigateToBack = navigateToBack,
            getDateTasteSavedStateHandle = getDateTasteSavedStateHandle,
            getIdealTypeSavedStateHandle = getIdealTypeSavedStateHandle,
        )
    }
}

@Serializable
data object AddMatching : Route
