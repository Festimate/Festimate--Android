package com.mtc.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mtc.addmatching.AddMatching
import com.mtc.datetaste.navigation.DateTaste
import com.mtc.home.Home
import com.mtc.idealtype.navigation.IdealType
import com.mtc.login.navigation.Login
import com.mtc.login.navigation.SignUp
import com.mtc.model.IdealTypeInfo
import com.mtc.navigation.Route
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Home
    // login으로 돌리기

    fun navigateToLogin() {
        navController.navigate(Login) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    fun navigateToSignup() {
        navController.navigate(SignUp) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    fun navigateToHome() {
        navController.navigate(Home) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    fun navigateToAddMatching() {
        navController.navigate(AddMatching) {
            popUpTo(Home) {
                inclusive = false
            }
        }
    }

    fun navigateToIdealType() {
        navController.navigate(IdealType)
    }

    fun navigateToDateTaste() {
        navController.navigate(DateTaste)
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun setIdealTypeSavedStateHandle(
        minAge: String,
        maxAge: String,
        minHeight: String,
        maxHeight: String,
        mbti: String,
        appearanceList: List<String>,
    ) {
        val idealTypeList = Json.encodeToString(
            IdealTypeInfo(
                minAge,
                maxAge,
                minHeight,
                maxHeight,
                mbti,
                appearanceList,
            ),
        )
        navController.previousBackStackEntry?.savedStateHandle?.set(IDEAL_TYPE, idealTypeList)
    }

    fun setDateTasteSavedStateHandle(dateTasteList: List<Int>) {
        navController.previousBackStackEntry?.savedStateHandle?.set(DATE_TASTE, dateTasteList)
    }

    fun getIdealTypeSavedStateHandle() =
        navController.currentBackStackEntry?.savedStateHandle?.get<String>(IDEAL_TYPE)

    fun getDateTasteSavedStateHandle() =
        navController.currentBackStackEntry?.savedStateHandle?.get<List<Int>>(DATE_TASTE)

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean {
        return navController.currentDestination?.hasRoute<T>() == true
    }

    companion object {
        const val IDEAL_TYPE = "idealType"
        const val DATE_TASTE = "dateTaste"
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
