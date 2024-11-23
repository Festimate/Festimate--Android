package com.mtc.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateAddMatching: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val pagerState = rememberPagerState(pageCount = { if (state.matchingInfo.isEmpty()) 1 else state.matchingInfo.size })
        viewModel.updateMatchingList()
        Text(
            modifier = Modifier.clickable {
                navigateAddMatching()
            },
            text = "home",
        )
        MatchingListPager(
            modifier = modifier,
            pagerState = pagerState,
            homeState = state,
        )
    }
}
