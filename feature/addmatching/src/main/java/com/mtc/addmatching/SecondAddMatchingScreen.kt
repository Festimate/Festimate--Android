package com.mtc.addmatching

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.mtc.designsystem.R
import com.mtc.designsystem.component.FestimateBasicButton
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray01
import com.mtc.designsystem.theme.Gray04
import com.mtc.designsystem.theme.Gray06
import com.mtc.designsystem.theme.MainCoral
import com.mtc.designsystem.theme.White

@Composable
fun SecondAddMatchingScreen(
    modifier: Modifier = Modifier,
    uiState: AddMatchingState,
    updatePossibleTime: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
    ) {
        val subscribeText = buildAnnotatedString {
            append("만날 수 있는 ")
            withStyle(style = SpanStyle(color = MainCoral)) {
                append("시간")
            }
            append("을\n모두 골라주세요!")
        }

        val possibleTile = listOf(
            "10 : 00",
            "11 : 00",
            "12 : 00",
            "13 : 00",
            "14 : 00",
            "15 : 00",
            "16 : 00",
            "17 : 00",
            "18 : 00",
            "19 : 00",
            "20 : 00",
            "21 : 00",
        )

        Row(
            modifier = Modifier.padding(top = 20.dp, bottom = 40.dp),
        ) {
            Column(
                modifier = Modifier.padding(top = 32.dp, bottom = 11.dp),
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 14.dp),
                    text = subscribeText,
                    style = FestimateTheme.typography.titleBold18,
                    color = Gray06,
                    textAlign = TextAlign.Start,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier.padding(end = 7.dp),
                        painter = painterResource(id = R.drawable.ic_login_item_info_16),
                        contentDescription = "item_info",
                    )
                    Text(
                        text = "다중 선택 가능",
                        style = FestimateTheme.typography.bodyMedium13,
                        color = Gray04,
                        textAlign = TextAlign.Center,
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier
                    .offset(x = 12.dp)
                    .clipToBounds(),
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = "time",
            )
        }
        Row(
            modifier = Modifier.padding(bottom = 22.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "가능한 시간대 설정하기",
                style = FestimateTheme.typography.bodySemibold15,
                color = Gray06,
            )
            Text(
                text = " *",
                style = FestimateTheme.typography.bodySemibold15,
                color = MainCoral,
            )
        }
        LazyVerticalGrid(
            modifier = modifier
                .padding(end = 16.dp)
                .fillMaxWidth(),
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            items(12) { item ->
                FestimateBasicButton(
                    text = possibleTile[item],
                    textColor = if (uiState.timeList.contains(possibleTile[item])) White else Gray04,
                    shape = RoundedCornerShape(11.dp),
                    textStyle = FestimateTheme.typography.bodySemibold15,
                    backgroundColor = if (uiState.timeList.contains(possibleTile[item])) MainCoral else Gray01,
                    onClick = { updatePossibleTime(possibleTile[item]) },
                    padding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
                )
            }
        }
    }
}
