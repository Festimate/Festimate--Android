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
import com.mtc.navigation.Route

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

    fun navigateToAddMatching(
        minAge: String? = null,
        maxAge: String? = null,
        minHeight: String? = null,
        maxHeight: String? = null,
        mbti: String? = null,
        appearanceList: List<String>? = null,
    ) {
        navController.navigate(
            AddMatching(
                minAge = minAge,
                maxAge = maxAge,
                minHeight = minHeight,
                maxHeight = maxHeight,
                mbti = mbti,
                apperanceList = appearanceList,
            ),
        ) {
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

    fun setIdealTypeSavedStateHandle() {
        navController.previousBackStackEntry?.savedStateHandle?.set("idealType", "")
    }
    fun setDateTasteSavedStateHandle(dateTasteList: List<Int>) {
        navController.previousBackStackEntry?.savedStateHandle?.set("dateTaste", dateTasteList)
    }

    fun getIdealTypeSavedStateHandle() =
        navController.currentBackStackEntry?.savedStateHandle?.get<>("idealType")

    fun getDateTasteSavedStateHandle() =
        navController.currentBackStackEntry?.savedStateHandle?.get<List<Int>>("dateTaste")

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean {
        return navController.currentDestination?.hasRoute<T>() == true
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
