package com.mtc.datetaste

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mtc.designsystem.R
import com.mtc.designsystem.component.FestimateBasicButton
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray01
import com.mtc.designsystem.theme.Gray03
import com.mtc.designsystem.theme.Gray06
import com.mtc.designsystem.theme.MainCoral
import com.mtc.designsystem.theme.White
import com.mtc.ui.extension.customClickable
import com.mtc.ui.lifecycle.LaunchedEffectWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun DateTasteRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateToBack: () -> Unit,
    setDateTasteSavedStateHandle: (List<Int>) -> Unit,
    viewModel: DateTasteViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        viewModel.apply {
            dateTasteScreenValidate()
        }
    }

    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                DateTasteSideEffect.Empty -> {}
                DateTasteSideEffect.Error -> {}
                DateTasteSideEffect.Loading -> {}
                DateTasteSideEffect.Success -> {
                    setDateTasteSavedStateHandle(uiState.dateTasteList)
                    navigateToBack()
                }
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
        modifier = Modifier
            .fillMaxSize(),
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

        Box(
            modifier = Modifier
                .padding(bottom = 33.dp),
        ) {
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
        Box(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp)
                .padding(bottom = 7.dp)
                .background(
                    color = MainCoral,
                    shape = CircleShape,
                )
                .size(30.dp)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = (pagerState.currentPage + 1).toString(),
                style = FestimateTheme.typography.bodyBold15,
                color = White,
                textAlign = TextAlign.Center,
            )
        }
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(
                    bottom = when (pagerState.currentPage) {
                        0, 2, 4 -> 91.dp
                        1, 3 -> 44.dp
                        else -> 0.dp
                    },
                )
                .fillMaxWidth(),
            text = when (pagerState.currentPage) {
                0 -> "이성친구와 단둘이 만나도 될까요?"
                1 -> "얼마나 자주 데이트를 했으면 하나요?"
                2 -> "얼마나 자주 연락했으면 하나요?"
                3 -> "종교는 얼마나 중요한가요?"
                4 -> "맛집 웨이팅이 1시간 이상이라면?"
                else -> ""
            },
            style = FestimateTheme.typography.titleBold20,
            color = Gray06,
        )
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState,
            userScrollEnabled = false,
            verticalAlignment = Alignment.Top,
        ) { page ->
            DateTasteDetailScreen(
                uiState = uiState,
                pagerState = pagerState,
                firstText = when (pagerState.currentPage) {
                    0 -> "OK ~ 난 쿨하니까 허락해요!"
                    1 -> "주 1 ~ 2회"
                    2 -> "연인이라면 사소한 일상도 공유 필수!"
                    3 -> "저는 종교가 없어서 안중요해요"
                    4 -> "너무 길어요 ㅠ 다른 식당 가요"
                    else -> ""
                },
                secondText = when (pagerState.currentPage) {
                    0 -> "No! 단 둘이 만나는 건 자제해요!"
                    1 -> "주 3 ~ 4회"
                    2 -> "여유로울 때만 연락해도 괜찮아요~"
                    3 -> "저는 매주 종교생활을 해야해요"
                    4 -> "그래도 기다려서 먹어보고 싶어요!"
                    else -> ""
                },
                thirdText = when (pagerState.currentPage) {
                    0 -> ""
                    1 -> "주 5회 이상"
                    2 -> ""
                    3 -> "상관없이 모두 존중해요"
                    4 -> ""
                    else -> ""
                },
                onButtonClick =
                when (pagerState.currentPage) {
                    0 -> viewModel::updateFirstDateTasteQuestion
                    1 -> viewModel::updateSecondDateTasteQuestion
                    2 -> viewModel::updateThirdDateTasteQuestion
                    3 -> viewModel::updateFourthDateTasteQuestion
                    4 -> viewModel::updateFifthDateTasteQuestion
                    else -> viewModel::updateFirstDateTasteQuestion
                },
            )
        }
        FestimateBasicButton(
            Modifier
                .padding(bottom = 18.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            text = if (pagerState.currentPage == 4) "완료" else "다음",
            textStyle = FestimateTheme.typography.bodySemibold17,
            clickable = when (pagerState.currentPage) {
                0 -> uiState.firstQuestion != 0
                1 -> uiState.secondQuestion != 0
                2 -> uiState.thirdQuestion != 0
                3 -> uiState.fourthQuestion != 0
                4 -> uiState.dateTasteScreenResult
                else -> false
            },
            backgroundColor = when (pagerState.currentPage) {
                0 -> if (uiState.firstQuestion != 0) MainCoral else Gray03
                1 -> if (uiState.secondQuestion != 0) MainCoral else Gray03
                2 -> if (uiState.thirdQuestion != 0) MainCoral else Gray03
                3 -> if (uiState.fourthQuestion != 0) MainCoral else Gray03
                4 -> if (uiState.fifthQuestion != 0) MainCoral else Gray03
                else -> Gray03
            },
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
