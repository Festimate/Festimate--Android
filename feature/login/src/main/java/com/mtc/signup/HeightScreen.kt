package com.mtc.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mtc.designsystem.component.FestimateBasicButton
import com.mtc.designsystem.component.FestimateBasicTextField
import com.mtc.designsystem.component.FestimateTopAppBar
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray01
import com.mtc.designsystem.theme.Gray04
import com.mtc.designsystem.theme.Gray06
import com.mtc.designsystem.theme.MainCoral

@Composable
fun HeightScreen(
    modifier: Modifier = Modifier,
    uiState: SignUpState,
) {
    val mbti = listOf("E", "I", "N", "S", "F", "T", "P", "J")
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        FestimateTopAppBar(
            signupPageNumber = "2",
            signupPageContent = "내 정보 입력",
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "키 ",
                style = FestimateTheme.typography.bodySemibold17,
                color = Gray06,
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = modifier.padding(end = 8.dp),
                text = "*",
                style = FestimateTheme.typography.bodySemibold17,
                color = MainCoral,
                textAlign = TextAlign.Center,
            )
        }
        Row(
            modifier = modifier.padding(top = 14.dp, bottom = 22.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FestimateBasicTextField(
                modifier = modifier
                    .fillMaxWidth(0.45f)
                    .padding(end = 10.dp),
                shape = RoundedCornerShape(12.dp),
                placeholder = "키를 입력해주세요",
                textStyle = FestimateTheme.typography.bodyMedium13,
                textColor = Gray04,
            )
            Text(
                text = "cm",
                style = FestimateTheme.typography.bodySemibold17,
                color = Gray06,
                textAlign = TextAlign.Center,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "MBTI ",
                style = FestimateTheme.typography.bodySemibold17,
                color = Gray06,
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = modifier.padding(end = 8.dp, bottom = 14.dp),
                text = "*",
                style = FestimateTheme.typography.bodySemibold17,
                color = MainCoral,
                textAlign = TextAlign.Center,
            )
        }
        LazyVerticalGrid(
            modifier = modifier
                .fillMaxWidth(),
            columns = GridCells.Adaptive(minSize = 128.dp),
            horizontalArrangement = Arrangement.spacedBy(13.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            items(8) { item ->
                FestimateBasicButton(
                    text = mbti[item],
                    textColor = Gray04,
                    shape = RoundedCornerShape(11.dp),
                    textStyle = FestimateTheme.typography.bodySemibold15,
                    backgroundColor = Gray01,
                    onClick = {},
                    padding = PaddingValues(horizontal = 78.dp, vertical = 15.dp),
                )
            }

        }
    }
}