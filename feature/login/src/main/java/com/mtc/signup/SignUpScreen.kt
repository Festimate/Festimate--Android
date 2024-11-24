package com.mtc.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mtc.designsystem.R
import com.mtc.designsystem.component.FestimateBasicButton
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray03
import com.mtc.signup.SignUpPage.Companion.toSignupPager
import com.mtc.ui.extension.customClickable
import com.mtc.ui.lifecycle.LaunchedEffectWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                SignUpSideEffect.Empty -> {}
                SignUpSideEffect.Error -> {}
                SignUpSideEffect.Loading -> {}
                SignUpSideEffect.Success -> navigateToHome()
            }
        }
    }

    SignUpScreen(
        modifier = modifier,
        uiState = uiState,
        viewModel = viewModel,
    )
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    uiState: SignUpState,
    viewModel: SignUpViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })

    Column {
        Image(
            modifier = modifier
                .align(Alignment.Start)
                .padding(start = 16.dp, top = 50.dp)
                .alpha(
                    if (pagerState.currentPage == 1 || pagerState.currentPage == 2) 1f else 0f,
                )
                .customClickable(
                    rippleEnabled = false,
                    onClick = if (pagerState.currentPage == 1 || pagerState.currentPage == 2) {
                        {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    } else null,
                ),
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "back",
        )
        HorizontalPager(
            modifier = modifier.fillMaxWidth(),
            state = pagerState,
            userScrollEnabled = true,
        ) { page ->
            when (page.toSignupPager()) {
                SignUpPage.Error -> {}
                SignUpPage.Name -> NameScreen(
                    uiState = uiState,
                )

                SignUpPage.Height -> HeightScreen(
                    uiState = uiState,
                )

                SignUpPage.Appearance -> AppearanceScreen(
                    uiState = uiState,
                )
            }
        }
        FestimateBasicButton(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(10.dp),
            text = "다음",
            textStyle = FestimateTheme.typography.bodySemibold17,
            clickable = true,
            backgroundColor = Gray03,
            onClick = {
                if (pagerState.currentPage == 0 || pagerState.currentPage == 1) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else viewModel.signUp()

            },
            padding = PaddingValues(horizontal = 156.dp, vertical = 17.dp),
        )
    }
}
