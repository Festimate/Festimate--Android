package com.mtc.signup.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mtc.navigation.Route
import com.mtc.signup.SignupRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSignup() {
    navigate(Signup)
}

fun NavGraphBuilder.signupNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    composable<Signup> {
        SignupRoute()
    }
}

@Serializable
data object Signup : Route
