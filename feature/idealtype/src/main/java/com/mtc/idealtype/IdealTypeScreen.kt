package com.mtc.idealtype

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mtc.designsystem.R
import com.mtc.designsystem.component.FestimateBasicButton
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray03
import com.mtc.designsystem.theme.MainCoral
import com.mtc.idealtype.IdealTypePage.Companion.toSignupPager
import com.mtc.ui.extension.customClickable
import com.mtc.ui.lifecycle.LaunchedEffectWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun IdealTypeRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateAddMatching: () -> Unit,
    viewModel: IdealTypeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        viewModel.apply {
            firstUserInfoScreenResultValidate()
        }
    }

    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                IdealTypeSideEffect.Empty -> {}
                IdealTypeSideEffect.Error -> {}
                IdealTypeSideEffect.Loading -> {}
                IdealTypeSideEffect.Success -> navigateAddMatching()
            }
        }
    }

    IdealTypeScreen(
        modifier = modifier,
        uiState = uiState,
        viewModel = viewModel,
    )
}

@Composable
fun IdealTypeScreen(
    modifier: Modifier = Modifier,
    uiState: IdealTypeState,
    viewModel: IdealTypeViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })

    BackHandler(enabled = pagerState.currentPage == 1 || pagerState.currentPage == 2) {
        coroutineScope.launch {
            when (pagerState.currentPage) {
                1, 2 -> pagerState.animateScrollToPage(
                    pagerState.currentPage - 1,
                )
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 50.dp)
                .padding(horizontal = 16.dp)
                .customClickable(
                    rippleEnabled = false,
                    onClick = if (pagerState.currentPage == 1 || pagerState.currentPage == 2) {
                        {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    } else {
                        null
                    },
                ),
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "back",
        )
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState,
            userScrollEnabled = false,
            verticalAlignment = Alignment.Top,
        ) { page ->
            when (page.toSignupPager()) {
                IdealTypePage.Error -> {}
                IdealTypePage.FirstIdealType -> FirstIdealTypeScreen(
                    uiState = uiState,
                    updateMinAge = viewModel::updateMinAge,
                    updateMaxAge = viewModel::updateMaxAge,
                    updateMinHeight = viewModel::updateMinHeight,
                    updateMaxHeight = viewModel::updateMaxHeight,
                    updateMbti = viewModel::updateMbti,
                )

                IdealTypePage.SecondIdealType -> SecondIdealTypeScreen(
                    uiState = uiState,
                )

                IdealTypePage.ThirdIdealType -> ThirdIdealTypeScreen(
                    uiState = uiState,
                )
            }
        }
        FestimateBasicButton(
            Modifier
                .padding(bottom = 18.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            text = "다음",
            textStyle = FestimateTheme.typography.bodySemibold17,
            clickable = when (pagerState.currentPage) {
                0 -> uiState.firstIdealTypeScreenResult
                1 -> uiState.secondIdealTypeScreenResult
                2 -> uiState.thirdIdealTypeScreenResult
                else -> false
            },
            backgroundColor = when (pagerState.currentPage) {
                0 -> if (uiState.firstIdealTypeScreenResult) MainCoral else Gray03
                1 -> if (uiState.secondIdealTypeScreenResult) MainCoral else Gray03
                2 -> if (uiState.thirdIdealTypeScreenResult) MainCoral else Gray03
                else -> Gray03
            },
            onClick = {
                if (pagerState.currentPage == 0 || pagerState.currentPage == 1) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    viewModel.updateIdealTypeResult()
                }
            },
            padding = PaddingValues(horizontal = 156.dp, vertical = 17.dp),
        )
    }
}
