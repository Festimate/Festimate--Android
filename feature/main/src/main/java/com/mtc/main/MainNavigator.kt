package com.mtc.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mtc.addmatching.AddMatching
import com.mtc.home.Home
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

    fun navigateToAddMatching() {
        navController.navigate(AddMatching) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

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
