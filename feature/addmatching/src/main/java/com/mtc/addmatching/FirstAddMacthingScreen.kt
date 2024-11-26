package com.mtc.addmatching

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mtc.designsystem.R
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray02
import com.mtc.designsystem.theme.Gray04
import com.mtc.designsystem.theme.Gray05
import com.mtc.designsystem.theme.Gray06
import com.mtc.designsystem.theme.MainCoral
import com.mtc.designsystem.theme.SubCoral
import com.mtc.designsystem.theme.White
import com.mtc.ui.extension.customClickable

@Composable
fun FirstAddMatchingScreen(
    modifier: Modifier = Modifier,
    uiState: AddMatchingState,
    navigateIdealType: () -> Unit,
    navigateDateTaste: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(top = 31.dp),
            text = "두근두근",
            style = FestimateTheme.typography.titleBold20,
            color = Gray06,
        )
        Row(
            modifier = Modifier.padding(top = 2.dp, bottom = 9.dp),
        ) {
            Text(
                text = "나의 ",
                style = FestimateTheme.typography.titleBold20,
                color = Gray06,
            )
            Text(
                text = "FestiMate",
                style = FestimateTheme.typography.titleBold20,
                color = MainCoral,
            )
            Text(
                text = "를 찾아봐요",
                style = FestimateTheme.typography.titleBold20,
                color = Gray06,
            )
        }
        Row(
            modifier = Modifier.padding(bottom = 112.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = Modifier.padding(end = 7.dp),
                painter = painterResource(id = R.drawable.ic_login_item_info_16),
                contentDescription = "item_info",
            )
            Text(
                text = "2개의 항목을 모두 입력해주세요",
                style = FestimateTheme.typography.bodyMedium13,
                color = Gray04,
                textAlign = TextAlign.Center,
            )
        }
        AddMatchingBox(
            modifier = Modifier.padding(bottom = 16.dp),
            onClick = { navigateIdealType() },
            success = uiState.idealTypeResult,
            title = "이상형",
            context = "나는 이런 사람과 매칭되고 싶어요!",
        )
        AddMatchingBox(
            onClick = { navigateDateTaste() },
            success = uiState.dateTasteResult,
            title = "연애 취향",
            context = "나와 얼마나 잘 맞을까?",
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp, end = 127.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_matching_subscribe),
                contentDescription = "matching_subscribe",
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 11.dp, end = 12.dp)
                    .align(Alignment.Center),
                text = "모두 입력해야 매칭이 시작됩니다!",
                style = FestimateTheme.typography.bodySemibold13,
                color = MainCoral,
            )
        }
    }
}

@Composable
fun AddMatchingBox(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    success: Boolean,
    title: String,
    context: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(11.dp))
            .background(
                color = if (success) SubCoral else White,
                shape = RoundedCornerShape(11.dp),
            )
            .border(
                width = 2.dp,
                color = if (success) MainCoral else Gray02,
                shape = RoundedCornerShape(11.dp),
            )
            .customClickable(
                runIf = true,
                rippleColor = White,
                rippleEnabled = false,
                onClick = onClick,
            )
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp),
                text = title,
                style = FestimateTheme.typography.titleBold18,
                color = Gray05,
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = context,
                style = FestimateTheme.typography.bodyMedium13,
                color = Gray04,
                textAlign = TextAlign.Center,
            )
        }
    }
}
