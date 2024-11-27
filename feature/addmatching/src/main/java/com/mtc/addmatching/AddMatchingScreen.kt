package com.mtc.addmatching

import android.util.Log
import androidx.activity.compose.BackHandler
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
import com.mtc.addmatching.AddMatchingPage.Companion.toAddMatching
import com.mtc.designsystem.R
import com.mtc.designsystem.component.FestimateBasicButton
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.MainCoral
import com.mtc.model.IdealTypeInfo
import com.mtc.ui.extension.customClickable
import com.mtc.ui.lifecycle.LaunchedEffectWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AddMatchingRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateIdealType: () -> Unit,
    navigateDateTaste: () -> Unit,
    navigateHome: () -> Unit,
    idealTypeInfo: IdealTypeInfo,
    getDateTasteSavedStateHandle: () -> List<Int>?,
    getIdealTypeSavedStateHandle: () -> List<Any>?,
    viewModel: AddMatchingViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        Log.d("dateTaste", getDateTasteSavedStateHandle().toString())
        Log.d("dateTaste->ideal", getIdealTypeSavedStateHandle().toString())
    }
    LaunchedEffect(idealTypeInfo) {
        viewModel.updateIdealTypeInfo(idealTypeInfo)
    }
    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                AddMatchingSideEffect.Empty -> {}
                AddMatchingSideEffect.Error -> {}
                AddMatchingSideEffect.Loading -> {}
                AddMatchingSideEffect.Success -> navigateHome()
                AddMatchingSideEffect.IdealType -> navigateIdealType()
                AddMatchingSideEffect.DateTaste -> navigateDateTaste()
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
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 4 })

    BackHandler(enabled = pagerState.currentPage == 1 || pagerState.currentPage == 2 || pagerState.currentPage == 3) {
        coroutineScope.launch {
            when (pagerState.currentPage) {
                1, 2, 3 -> pagerState.animateScrollToPage(
                    pagerState.currentPage - 1,
                )
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (pagerState.currentPage == 0) {
            Image(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .align(Alignment.TopEnd),
                painter = painterResource(id = R.drawable.ic_character_rt_32),
                contentDescription = "character",
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 50.dp)
                    .padding(horizontal = 16.dp)
                    .customClickable(
                        rippleEnabled = false,
                        onClick = if (pagerState.currentPage == 1 || pagerState.currentPage == 2 || pagerState.currentPage == 3) {
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
                userScrollEnabled = true,
                verticalAlignment = Alignment.Top,
            ) { page ->
                when (page.toAddMatching()) {
                    AddMatchingPage.Error -> {}
                    AddMatchingPage.FirstAddMatching -> FirstAddMatchingScreen(
                        uiState = uiState,
                        navigateIdealType = viewModel::navigateIdealType,
                        navigateDateTaste = viewModel::navigateDateTaste,
                    )

                    AddMatchingPage.SecondAddMatching -> SecondAddMatchingScreen(
                        uiState = uiState,
                    )

                    AddMatchingPage.ThirdAddMatching -> ThirdAddMatchingScreen(
                        uiState = uiState,
                    )

                    AddMatchingPage.FourthAddMatching -> FourthAddMatchingScreen(
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
                clickable = true,
                backgroundColor = MainCoral,
                onClick = {
                },
                padding = PaddingValues(horizontal = 156.dp, vertical = 17.dp),
            )
        }
    }
}
