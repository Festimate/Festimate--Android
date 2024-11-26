package com.mtc.datetaste

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mtc.navigation.Route
import kotlinx.serialization.Serializable

fun NavController.navigateDateTaste(navOptions: NavOptions) {
    navigate(DateTaste, navOptions)
}

fun NavGraphBuilder.dateTasteNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateToAddMatching: () -> Unit,
) {
    composable<DateTaste> {
        DateTasteRoute(
            padding = padding,
            modifier = modifier,
            navigateAddMatching = navigateToAddMatching,
        )
    }
}

@Serializable
data object DateTaste : Route
