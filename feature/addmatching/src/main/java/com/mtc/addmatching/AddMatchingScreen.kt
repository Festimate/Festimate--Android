package com.mtc.addmatching

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mtc.ui.lifecycle.LaunchedEffectWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddMatchingRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateHome: () -> Unit,
    viewModel: AddMatchingViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                AddMatchingSideEffect.Empty -> {}
                AddMatchingSideEffect.Error -> {}
                AddMatchingSideEffect.Loading -> {}
                AddMatchingSideEffect.Success -> navigateHome()
            }
        }
    }
    AddMatchingScreen(
        modifier = modifier,
        uiState = uiState,
        viewModel = viewModel,
    )
}

@Composable
fun AddMatchingScreen(
    modifier: Modifier = Modifier,
    uiState: AddMatchingState,
    viewModel: AddMatchingViewModel,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "matching")
    }
}
