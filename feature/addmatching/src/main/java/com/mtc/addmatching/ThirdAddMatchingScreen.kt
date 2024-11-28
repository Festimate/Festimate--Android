package com.mtc.addmatching

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import com.mtc.designsystem.component.FestimateBasicTextField
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray04
import com.mtc.designsystem.theme.Gray06
import com.mtc.designsystem.theme.MainCoral

@Composable
fun ThirdAddMatchingScreen(
    modifier: Modifier = Modifier,
    uiState: AddMatchingState,
    updateCloth: (String) -> Unit,
) {
    val subscribeText = buildAnnotatedString {
        append("당신을 찾을 수 있는\n")
        withStyle(style = SpanStyle(color = MainCoral)) {
            append("착장 정보")
        }
        append("를 알려주세요!")
    }

    Box(
        modifier = Modifier
            .clipToBounds()
            .fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier
                .padding(top = 6.dp)
                .align(Alignment.TopEnd)
                .offset(x = 56.dp),
            painter = painterResource(id = R.drawable.ic_cloth),
            contentDescription = "cloth",
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 52.dp)
                .padding(horizontal = 16.dp),
        ) {
            Text(
                modifier = Modifier.padding(bottom = 14.dp),
                text = subscribeText,
                style = FestimateTheme.typography.titleBold18,
                color = Gray06,
                textAlign = TextAlign.Start,
            )
            Row(
                modifier = Modifier.padding(bottom = 50.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier.padding(end = 7.dp),
                    painter = painterResource(id = R.drawable.ic_login_item_info_16),
                    contentDescription = "item_info",
                )
                Text(
                    text = "오프라인에서 Festimate를 찾는데 도움이 돼요!",
                    style = FestimateTheme.typography.bodyMedium13,
                    color = Gray04,
                    textAlign = TextAlign.Center,
                )
            }
            InputClothBox(
                uiState = uiState,
                updateCloth = updateCloth,
            )
        }
    }
}

@Composable
fun InputClothBox(
    modifier: Modifier = Modifier,
    uiState: AddMatchingState,
    updateCloth: (String) -> Unit,
) {
    Column {
        Box(
            modifier = Modifier
                .padding(bottom = 14.dp)
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_success_matching_content),
                contentDescription = "input_cloth",
            )
            FestimateBasicTextField(
                modifier = Modifier.padding(vertical = 29.dp, horizontal = 16.dp),
                value = uiState.cloth,
                onValueChange = { updateCloth(it) },
                shape = RoundedCornerShape(12.dp),
                placeholder = "ex) 저는 회색 가디건에 청치마를 입고 있어요!",
                textStyle = FestimateTheme.typography.bodyMedium15,
                textColor = Gray04,
                maxLength = 50,
                maxLines = 5,
            )
        }
        Text(
            modifier = Modifier.align(Alignment.End),
            text = "${uiState.cloth.length}/50",
            style = FestimateTheme.typography.bodyMedium13,
            color = Gray04,
        )
    }

}
