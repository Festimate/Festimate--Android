package com.mtc.idealtype

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mtc.designsystem.R
import com.mtc.designsystem.component.FestimateBasicButton
import com.mtc.designsystem.component.FestimateTopAppBar
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray01
import com.mtc.designsystem.theme.Gray04
import com.mtc.designsystem.theme.MainCoral
import com.mtc.designsystem.theme.White
import com.mtc.model.Appearance
import com.mtc.model.Appearance.Companion.toAppearance

@Composable
fun SecondIdealTypeScreen(
    modifier: Modifier = Modifier,
    uiState: IdealTypeState,
    updateAppearance: (Appearance) -> Unit,
) {
    val appearance = listOf(
        "고양이상",
        "강아지상",
        "공룡상",
        "여우상",
        "곰상",
        "토끼상",
        "아랍상",
        "두부상",
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FestimateTopAppBar(
            signupPageNumber = "2",
            signupPageContent = "이상형인 얼굴상은?",
        )
        Row(
            modifier = modifier.padding(bottom = 30.dp),
        ) {
            Image(
                modifier = modifier.padding(end = 2.dp),
                painter = painterResource(id = R.drawable.ic_login_item_info_16),
                contentDescription = "item_info",
            )
            Text(
                text = "최대 2개까지 선택 가능해요",
                style = FestimateTheme.typography.bodyMedium13,
                color = Gray04,
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
                    text = appearance[item],
                    textColor = if (item.toAppearance() == uiState.firstAppearance ||
                        item.toAppearance() == uiState.secondAppearance
                    ) {
                        White
                    } else {
                        Gray04
                    },
                    shape = RoundedCornerShape(11.dp),
                    textStyle = FestimateTheme.typography.bodySemibold15,
                    backgroundColor = if (item.toAppearance() == uiState.firstAppearance ||
                        item.toAppearance() == uiState.secondAppearance
                    ) {
                        MainCoral
                    } else {
                        Gray01
                    },
                    onClick = { updateAppearance(item.toAppearance()) },
                    padding = PaddingValues(horizontal = 56.dp, vertical = 35.dp),
                )
            }
        }
    }
}
