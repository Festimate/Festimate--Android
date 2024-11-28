package com.mtc.addmatching

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mtc.designsystem.R
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray01
import com.mtc.designsystem.theme.Gray04
import com.mtc.designsystem.theme.Gray05
import com.mtc.designsystem.theme.Gray06
import com.mtc.designsystem.theme.White
import com.mtc.ui.extension.customClickable
import kotlinx.coroutines.delay

@Composable
fun FourthAddMatchingScreen(
    modifier: Modifier = Modifier,
    uiState: AddMatchingState,
) {
    val clipboardManager = LocalClipboardManager.current
    val accountCopyState = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.padding(bottom = 30.dp),
            painter = painterResource(id = R.drawable.ic_character),
            contentDescription = "character",
        )
        Text(
            modifier = Modifier.padding(bottom = 14.dp),
            text = "매칭 신청이 완료되었어요!",
            style = FestimateTheme.typography.titleBold20,
            color = Gray06,
        )
        Text(
            modifier = Modifier.padding(bottom = 33.dp),
            text = "다음 계좌로 입금이 확인되면,\n매칭을 시작해드릴게요!",
            style = FestimateTheme.typography.bodySemibold15,
            color = Gray05,
            textAlign = TextAlign.Center,
        )
        Box(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(9.dp))
                .background(
                    color = Gray01,
                    shape = RoundedCornerShape(9.dp),
                ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 22.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier.padding(end = 7.dp),
                    painter = painterResource(id = R.drawable.ic_bank_account),
                    contentDescription = "bank_account",
                )
                Text(
                    text = "${uiState.bankName} ${uiState.bankAccount}",
                    style = FestimateTheme.typography.bodyMedium17,
                    color = Gray06,
                )
            }
        }
        Row(
            modifier = Modifier
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    drawRect(
                        color = Gray04,
                        topLeft = Offset(0f, size.height - strokeWidth),
                        size = Size(size.width, strokeWidth),
                    )
                }
                .customClickable(
                    onClick = {
                        clipboardManager.setText(AnnotatedString(uiState.bankAccount))
                        accountCopyState.value = true
                    },
                ),
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier = Modifier.padding(bottom = 2.dp),
                painter = painterResource(id = R.drawable.ic_account),
                contentDescription = "copy_account",
            )
            Text(
                text = "계좌번호 복사하기",
                style = FestimateTheme.typography.bodyMedium13,
                color = Gray04,
            )
        }
        Spacer(modifier = Modifier.padding(top = 39.dp))
        if (accountCopyState.value) {
            LaunchedEffect(Unit) {
                delay(1200L)
                accountCopyState.value = false
            }
            AnimatedVisibility(
                visible = accountCopyState.value,
                enter = fadeIn(animationSpec = tween(durationMillis = 300)),
                exit = fadeOut(animationSpec = tween(durationMillis = 900)),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 47.dp)
                        .clip(shape = RoundedCornerShape(9.dp))
                        .background(
                            color = Gray05,
                            shape = RoundedCornerShape(9.dp),
                        )
                        .padding(vertical = 23.dp),
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "계좌 번호가 복사되었어요",
                        style = FestimateTheme.typography.bodyMedium15,
                        color = White,
                    )
                }
            }
        }
    }
}
