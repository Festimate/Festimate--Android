package com.mtc.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mtc.login.LoginRoute
import com.mtc.navigation.Route
import kotlinx.serialization.Serializable

fun NavController.navigateLogin(navOptions: NavOptions) {
    navigate(Login, navOptions)
}

fun NavGraphBuilder.signupNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    composable<Signup> {
        LoginRoute()
    }
}

@Serializable
data object Login : Route

@Serializable
data object Signup : Route

