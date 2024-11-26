package com.mtc.signup

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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mtc.designsystem.R
import com.mtc.designsystem.component.FestimateBasicButton
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray03
import com.mtc.designsystem.theme.MainCoral
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

    LaunchedEffect(uiState) {
        viewModel.apply {
            firstUserInfoScreenResultValidate()
            secondUserInfoScreenResultValidate()
        }
    }
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

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 50.dp)
                .padding(horizontal = 16.dp)
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
                SignUpPage.Error -> {}
                SignUpPage.Name -> FirstUserInfoScreen(
                    uiState = uiState,
                    updateName = viewModel::updateName,
                    updateNickname = viewModel::updateNickName,
                    checkNicknameDuplicate = viewModel::checkNickNameDuplicate,
                    updateAge = viewModel::updateAge,
                    updateGender = viewModel::updateGender,
                    updateSchool = viewModel::updateSchool,
                )

                SignUpPage.Height -> SecondUserInfoScreen(
                    uiState = uiState,
                    updateHeight = viewModel::updateHeight,
                    updateMbti = viewModel::updateMbti,
                )

                SignUpPage.Appearance -> ThirdUserInfoScreen(
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
                0 -> uiState.firstUserInfoScreenResult
                1 -> uiState.secondUserInfoScreenResult
                2 -> uiState.thirdUserInfoScreenResult
                else -> false
            },
            backgroundColor = when (pagerState.currentPage) {
                0 -> if (uiState.firstUserInfoScreenResult) MainCoral else Gray03
                1 -> if (uiState.secondUserInfoScreenResult) MainCoral else Gray03
                2 -> if (uiState.thirdUserInfoScreenResult) MainCoral else Gray03
                else -> Gray03
            },
            onClick = {
                if (pagerState.currentPage == 0 || pagerState.currentPage == 1) {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    viewModel.signUp()
                }
            },
            padding = PaddingValues(horizontal = 156.dp, vertical = 17.dp),
        )
    }
}
