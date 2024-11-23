package com.mtc.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mtc.designsystem.R
import com.mtc.designsystem.theme.FestimateTheme

@Composable
fun MatchingListPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    // state 바꿔야됨
    homeState: HomeState,
    contentPadding: Dp = 50.dp,
    pageSpacing: Dp = 16.dp,
) {
    Spacer(modifier = modifier.padding(top = 165.dp))

    HorizontalPager(
        modifier = modifier
            .fillMaxWidth(),
        state = pagerState,
        userScrollEnabled = true,
        contentPadding = PaddingValues(horizontal = contentPadding),
        pageSpacing = pageSpacing,
    ) { page ->
        when (homeState.matchingStateResult) {
            MatchingStateResult.Empty ->
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.img_empty_matching),
                        contentDescription = "empty",
                    )
                    Column(
                        modifier = modifier.fillMaxSize(),
                    ) {}
                }

            MatchingStateResult.Loading ->
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.img_loading_matching),
                        contentDescription = "loading",
                    )
                    Column(
                        modifier = modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = modifier.weight(0.40f))
                        Text(
                            modifier = modifier.align(Alignment.CenterHorizontally),
                            text = "이상형 조건에 맞는 MATE를\n찾는 중이니 조금만 기다려주세요!",
                            style = FestimateTheme.typography.bodySemibold15,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = modifier.weight(0.60f))
                    }
                }

            MatchingStateResult.Success ->
                MatchingContainer(matchingInfo = homeState.matchingInfo[page])
        }
    }
}
