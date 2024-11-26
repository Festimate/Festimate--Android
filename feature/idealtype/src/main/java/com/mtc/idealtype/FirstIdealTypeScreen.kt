package com.mtc.idealtype

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
import com.mtc.designsystem.component.FestimateTopAppBar
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray01
import com.mtc.designsystem.theme.Gray04
import com.mtc.designsystem.theme.Gray06
import com.mtc.designsystem.theme.MainCoral
import com.mtc.designsystem.theme.White
import com.mtc.idealtype.component.FestimateInputRangeTextField
import com.mtc.model.Mbti
import com.mtc.model.Mbti.Companion.toMbti

@Composable
fun FirstIdealTypeScreen(
    modifier: Modifier = Modifier,
    uiState: IdealTypeState,
    updateMinAge: (String) -> Unit,
    updateMaxAge: (String) -> Unit,
    updateMinHeight: (String) -> Unit,
    updateMaxHeight: (String) -> Unit,
    updateMbti: (Mbti) -> Unit,
) {
    val mbti = listOf("E", "I", "N", "S", "F", "T", "P", "J")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        FestimateTopAppBar(
            signupPageNumber = "1",
            signupPageContent = "내 이상형 정보 입력",
        )
        Row(
            modifier = Modifier.padding(bottom = 10.dp),
        ) {
            Text(
                text = "나이 ",
                style = FestimateTheme.typography.bodySemibold17,
                color = Gray06,
            )
            Text(
                text = "*",
                style = FestimateTheme.typography.bodySemibold17,
                color = MainCoral,
            )

        }
        FestimateInputRangeTextField(
            uiState = uiState,
            minPlaceholder = "최소",
            maxPlaceholder = "최대",
            subtitle = "세",
            minValue = uiState.minAge,
            maxValue = uiState.maxAge,
            updateMinValueChange = updateMinAge,
            updateMaxValueChange = updateMaxAge,
        )
        Row(
            modifier = Modifier.padding(top = 18.dp, bottom = 10.dp),
        ) {
            Text(
                text = "키 ",
                style = FestimateTheme.typography.bodySemibold17,
                color = Gray06,
            )
            Text(
                text = "*",
                style = FestimateTheme.typography.bodySemibold17,
                color = MainCoral,
            )

        }
        FestimateInputRangeTextField(
            uiState = uiState,
            minPlaceholder = "최소",
            maxPlaceholder = "최대",
            subtitle = "cm",
            minValue = uiState.minHeight,
            maxValue = uiState.maxHeight,
            updateMinValueChange = updateMinHeight,
            updateMaxValueChange = updateMaxHeight,
        )

        Row(
            modifier = Modifier.padding(top = 12.dp, bottom = 10.dp),
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
                    textColor = when (item.toMbti()) {
                        Mbti.E, Mbti.I -> {
                            if (item.toMbti() == uiState.m) {
                                White
                            } else {
                                Gray04
                            }
                        }

                        Mbti.N, Mbti.S -> {
                            if (item.toMbti() == uiState.b) {
                                White
                            } else {
                                Gray04
                            }
                        }

                        Mbti.F, Mbti.T -> {
                            if (item.toMbti() == uiState.t) {
                                White
                            } else {
                                Gray04
                            }
                        }

                        Mbti.P, Mbti.J -> {
                            if (item.toMbti() == uiState.i) {
                                White
                            } else {
                                Gray04
                            }
                        }

                        else -> Gray04
                    },
                    shape = RoundedCornerShape(11.dp),
                    textStyle = FestimateTheme.typography.bodySemibold15,
                    backgroundColor = when (item.toMbti()) {
                        Mbti.E, Mbti.I -> {
                            if (item.toMbti() == uiState.m) {
                                MainCoral
                            } else {
                                Gray01
                            }
                        }

                        Mbti.N, Mbti.S -> {
                            if (item.toMbti() == uiState.b) {
                                MainCoral
                            } else {
                                Gray01
                            }
                        }

                        Mbti.F, Mbti.T -> {
                            if (item.toMbti() == uiState.t) {
                                MainCoral
                            } else {
                                Gray01
                            }
                        }

                        Mbti.P, Mbti.J -> {
                            if (item.toMbti() == uiState.i) {
                                MainCoral
                            } else {
                                Gray01
                            }
                        }

                        else -> Gray01
                    },
                    onClick = { updateMbti(item.toMbti()) },
                    padding = PaddingValues(horizontal = 78.dp, vertical = 15.dp),
                )
            }
        }
    }
}
