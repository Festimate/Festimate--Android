package com.mtc.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
    homeState: HomeState,
    contentPadding: Dp = 50.dp,
    pageSpacing: Dp = 16.dp,
) {
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
                }

            MatchingStateResult.Loading ->
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.img_loading_matching),
                        contentDescription = "loading",
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.weight(0.7f))
                        Text(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            text = "이상형 조건에 맞는 MATE를\n찾는 중이니 조금만 기다려주세요!",
                            style = FestimateTheme.typography.bodySemibold15,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.weight(0.3f))
                    }
                }

            MatchingStateResult.Success ->
                MatchingContainer(matchingInfo = homeState.matchingInfo[page])
        }
    }
}
