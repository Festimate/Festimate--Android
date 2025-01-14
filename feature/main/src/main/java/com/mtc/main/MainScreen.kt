package com.mtc.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mtc.addmatching.addMatchingGraph
import com.mtc.datetaste.navigation.dateTasteNavGraph
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.home.homeNavGraph
import com.mtc.idealtype.navigation.idealTypeNavGraph
import com.mtc.login.navigation.loginNavGraph

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    navigator: MainNavigator = rememberMainNavigator(),
) {
    Scaffold(
        modifier = modifier,
        content = { innerPadding ->
            NavHost(
                modifier = modifier
                    .background(color = FestimateTheme.colors.white)
                    .fillMaxSize(),
                navController = navigator.navController,
                startDestination = navigator.startDestination,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
            ) {
                loginNavGraph(
                    padding = innerPadding,
                    navigateToHome = navigator::navigateToHome,
                    navigateToSignUp = navigator::navigateToSignup,
                    navigateToLogin = navigator::navigateToLogin,
                )
                homeNavGraph(
                    padding = innerPadding,
                    navigateToAddMatching = navigator::navigateToAddMatching,
                )
                addMatchingGraph(
                    padding = innerPadding,
                    navigateToIdealType = navigator::navigateToIdealType,
                    navigateToDateTaste = navigator::navigateToDateTaste,
                    navigateToHome = navigator::navigateToHome,
                    navigateToBack = navigator::navigateBack,
                    getDateTasteSavedStateHandle = navigator::getDateTasteSavedStateHandle,
                    getIdealTypeSavedStateHandle = navigator::getIdealTypeSavedStateHandle,
                )
                idealTypeNavGraph(
                    padding = innerPadding,
                    navigateToBack = navigator::navigateBack,
                    setIdealTypeSavedStateHandle = navigator::setIdealTypeSavedStateHandle,
                )
                dateTasteNavGraph(
                    padding = innerPadding,
                    navigateToBack = navigator::navigateBack,
                    setDateTasteSavedStateHandle = navigator::setDateTasteSavedStateHandle,
                )
            }
        },
    )
}
