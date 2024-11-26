package com.mtc.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mtc.designsystem.component.FestimateBasicButton
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray01
import com.mtc.designsystem.theme.Gray04
import com.mtc.designsystem.theme.Gray06
import com.mtc.designsystem.theme.MainCoral
import com.mtc.ui.lifecycle.LaunchedEffectWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateAddMatching: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        viewModel.apply {
            setAutoSignIn()
            getUserInfo()
            getMatchingList()
        }
    }
    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                HomeSideEffect.Empty -> {}
                HomeSideEffect.Loading -> {}
                HomeSideEffect.Success -> navigateAddMatching()
            }
        }
    }

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        viewModel = viewModel,
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier,
    uiState: HomeState,
    viewModel: HomeViewModel,
) {
    Column(
        modifier = modifier
            .background(Gray01)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
    ) {
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        val pagerState = rememberPagerState(pageCount = { if (state.matchingInfo.isEmpty()) 1 else state.matchingInfo.size })
        Text(
            modifier = Modifier.padding(start = 26.dp, top = 87.dp, bottom = 2.dp),
            text = "안녕하세요!",
            style = FestimateTheme.typography.titleBold20,
            color = Gray06,
        )
        Row(
            modifier = Modifier.padding(start = 26.dp, bottom = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = uiState.userNickname,
                style = FestimateTheme.typography.titleExtra24,
                color = MainCoral,
            )
            Text(
                text = "님",
                style = FestimateTheme.typography.titleBold20,
                color = Gray06,
            )
        }
        Row(
            modifier = Modifier.padding(start = 26.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.padding(end = 5.dp),
                painter = painterResource(id = com.mtc.designsystem.R.drawable.ic_university_hat),
                contentDescription = "school",
            )
            Text(
                text = uiState.userSchool,
                style = FestimateTheme.typography.bodySemibold15,
                color = Gray04,
            )
        }
        MatchingListPager(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 28.dp),
            pagerState = pagerState,
            homeState = state,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 89.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top,
        ) {
            Image(
                modifier = Modifier.padding(end = 7.dp),
                painter = painterResource(id = com.mtc.designsystem.R.drawable.ic_login_item_info_16),
                contentDescription = "matching_info",
            )
            Text(
                text = "Festimate 매칭은 15시, 17시에\n순차적으로 업데이트 됩니다!",
                style = FestimateTheme.typography.bodyMedium13,
                color = Gray04,
                textAlign = TextAlign.Center,
            )
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
            onClick = { viewModel.navigateAddMatching() },
            padding = PaddingValues(horizontal = 156.dp, vertical = 17.dp),
        )
    }
}
