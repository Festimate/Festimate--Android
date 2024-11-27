package com.mtc.datetaste

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mtc.datetaste.DateTastePage.Companion.toDateTastePage
import com.mtc.designsystem.R
import com.mtc.designsystem.component.FestimateBasicButton
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray01
import com.mtc.designsystem.theme.Gray03
import com.mtc.designsystem.theme.Gray06
import com.mtc.ui.extension.customClickable
import com.mtc.ui.lifecycle.LaunchedEffectWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun DateTasteRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateAddMatching: () -> Unit,
    viewModel: DateTasteViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        viewModel.apply {

        }
    }

    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                DateTasteSideEffect.Empty -> {}
                DateTasteSideEffect.Error -> {}
                DateTasteSideEffect.Loading -> {}
                DateTasteSideEffect.Success -> navigateAddMatching()
            }
        }
    }

    DateTasteScreen(
        modifier = modifier,
        uiState = uiState,
        viewModel = viewModel,
    )
}

@Composable
fun DateTasteScreen(
    modifier: Modifier = Modifier,
    uiState: DateTasteState,
    viewModel: DateTasteViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 5 })

    var fraction by remember { mutableFloatStateOf(0f) }
    val targetFraction = when (pagerState.currentPage) {
        0 -> 0.2f
        1 -> 0.4f
        2 -> 0.6f
        3 -> 0.8f
        4 -> 1.0f
        else -> 0f
    }
    val animatedFraction by animateFloatAsState(
        targetValue = fraction,
        animationSpec = tween(durationMillis = 500),
        label = "progress_indicator",
    )
    LaunchedEffect(pagerState.currentPage) {
        fraction = targetFraction
    }

    BackHandler(enabled = pagerState.currentPage != 0) {
        coroutineScope.launch {
            when (pagerState.currentPage) {
                1, 2, 3, 4 -> pagerState.animateScrollToPage(
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
                .padding(top = 50.dp, bottom = 27.dp)
                .padding(horizontal = 16.dp)
                .customClickable(
                    rippleEnabled = false,
                    onClick = if (pagerState.currentPage != 0) {
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

        Box(modifier = Modifier.padding(bottom = 33.dp)) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(),
                thickness = 6.dp,
                color = Gray01,
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(animatedFraction),
                thickness = 6.dp,
                color = Gray06,
            )
        }
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState,
            userScrollEnabled = true,
            verticalAlignment = Alignment.Top,
        ) { page ->
            when (page.toDateTastePage()) {
                DateTastePage.Error -> {}
                DateTastePage.FirstDateTaste -> FirstDateTasteScreen()
                DateTastePage.SecondDateTaste -> SecondDateTasteScreen()
                DateTastePage.ThirdDateTaste -> ThirdDateTasteScreen()
                DateTastePage.FourthDateTaste -> FourthDateTasteScreen()
                DateTastePage.FifthDateTaste -> FifthDateTasteScreen()
            }
        }
        FestimateBasicButton(
            Modifier
                .padding(bottom = 18.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            text = if (pagerState.currentPage == 4) "완료" else "다음",
            textStyle = FestimateTheme.typography.bodySemibold17,
            clickable = true,
            backgroundColor = Gray03,
            onClick = {
                if (pagerState.currentPage != 4) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    viewModel.updateDateTasteResult()
                }
            },
            padding = PaddingValues(horizontal = 156.dp, vertical = 17.dp),
        )
    }
}
