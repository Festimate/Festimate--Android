package com.mtc.datetaste

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray02
import com.mtc.designsystem.theme.Gray05
import com.mtc.designsystem.theme.MainCoral
import com.mtc.designsystem.theme.White
import com.mtc.ui.extension.customClickable

@Composable
fun DateTasteDetailScreen(
    modifier: Modifier = Modifier,
    uiState: DateTasteState,
    pagerState: PagerState,
    firstText: String,
    secondText: String,
    thirdText: String = "",
    onButtonClick: (Int) -> Unit,
) {
    val currentState = when (pagerState.currentPage) {
        0 -> uiState.firstQuestion
        1 -> uiState.secondQuestion
        2 -> uiState.thirdQuestion
        3 -> uiState.fourthQuestion
        4 -> uiState.fifthQuestion
        else -> 0
    }
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
    ) {
        DateTasteBox(
            title = firstText,
            success = currentState == 1,
            onClick = { onButtonClick(1) },
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        DateTasteBox(
            title = secondText,
            success = currentState == 2,
            onClick = { onButtonClick(2) },
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        if (pagerState.currentPage == 1 || pagerState.currentPage == 3) {
            DateTasteBox(
                title = thirdText,
                success = currentState == 3,
                onClick = { onButtonClick(3) },
            )
        }
    }
}

@Composable
fun DateTasteBox(
    modifier: Modifier = Modifier,
    title: String,
    success: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(11.dp))
            .background(
                color = if (success) MainCoral else White,
                shape = RoundedCornerShape(11.dp),
            )
            .border(
                width = 1.dp,
                color = if (success) MainCoral else Gray02,
                shape = RoundedCornerShape(11.dp),
            )
            .customClickable(
                runIf = true,
                rippleColor = White,
                rippleEnabled = false,
                onClick = onClick,
            )
            .padding(vertical = 33.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = title,
            style = FestimateTheme.typography.bodySemibold17,
            color = if (success) White else Gray05,
            textAlign = TextAlign.Center,
        )
    }
}
