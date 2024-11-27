package com.mtc.addmatching

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mtc.model.IdealTypeInfo
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
    getDateTasteSavedStateHandle: () -> List<Int>?,
    getIdealTypeSavedStateHandle: () -> List<Any>?,
) {
    composable<AddMatching> { backEntryState ->
        val idealTypeInfo = IdealTypeInfo(
            minAge = backEntryState.toRoute<AddMatching>().minAge,
            maxAge = backEntryState.toRoute<AddMatching>().maxAge,
            minHeight = backEntryState.toRoute<AddMatching>().minHeight,
            maxHeight = backEntryState.toRoute<AddMatching>().maxHeight,
            mbti = backEntryState.toRoute<AddMatching>().mbti,
            apperanceList = backEntryState.toRoute<AddMatching>().apperanceList,
        )
        AddMatchingRoute(
            padding = padding,
            modifier = modifier,
            navigateIdealType = navigateToIdealType,
            navigateDateTaste = navigateToDateTaste,
            navigateHome = navigateToHome,
            idealTypeInfo = idealTypeInfo,
            getDateTasteSavedStateHandle = getDateTasteSavedStateHandle,
            getIdealTypeSavedStateHandle = getIdealTypeSavedStateHandle,
        )
    }
}

@Serializable
data class AddMatching(
    val minAge: String?,
    val maxAge: String?,
    val minHeight: String?,
    val maxHeight: String?,
    val mbti: String?,
    val apperanceList: List<String>?,
) : Route
