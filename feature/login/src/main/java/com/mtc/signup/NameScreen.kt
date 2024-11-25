package com.mtc.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.mtc.designsystem.R
import com.mtc.designsystem.component.FestimateBasicButton
import com.mtc.designsystem.component.FestimateBasicTextField
import com.mtc.designsystem.component.FestimateTopAppBar
import com.mtc.designsystem.theme.AlertBlue
import com.mtc.designsystem.theme.AlertRed
import com.mtc.designsystem.theme.FestimateTheme
import com.mtc.designsystem.theme.Gray01
import com.mtc.designsystem.theme.Gray03
import com.mtc.designsystem.theme.Gray04
import com.mtc.designsystem.theme.Gray06
import com.mtc.designsystem.theme.MainCoral
import com.mtc.designsystem.theme.White
import com.mtc.model.NicknameValidateResult

@Composable
fun NameScreen(
    modifier: Modifier = Modifier,
    uiState: SignUpState,
    updateName: (String) -> Unit,
    updateNickname: (String) -> Unit,
    checkNicknameDuplicate: () -> Unit,
    updateAge: (String) -> Unit,
    updateGender: (SelectedGender) -> Unit,
    updateSchool: (String) -> Unit,
) {
    val nicknameHintColor: Color = when (uiState.nicknameValidateResult) {
        is NicknameValidateResult.KoreanError -> AlertRed
        is NicknameValidateResult.LengthError -> AlertRed
        is NicknameValidateResult.Success -> AlertBlue
        is NicknameValidateResult.CorrectInput -> AlertBlue
        is NicknameValidateResult.Duplicate -> AlertRed
        else -> Gray04
    }

    val nicknameHintText = when (uiState.nicknameValidateResult) {
        is NicknameValidateResult.KoreanError -> "한글만 입력 가능"
        is NicknameValidateResult.LengthError -> "최소 1자 - 최대 10자까지만 가능"
        is NicknameValidateResult.Success -> "사용 가능한 닉네임"
        is NicknameValidateResult.CorrectInput -> "사용 가능한 닉네임"
        is NicknameValidateResult.Duplicate -> "이미 사용중인 닉네임"
        else -> "한글만 가능, 최소 1자 - 최대 10자"
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        FestimateTopAppBar(
            signupPageNumber = "1",
            signupPageContent = "내 정보 입력",
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "이름 ",
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
            Image(
                modifier = modifier.padding(end = 2.dp),
                painter = painterResource(id = R.drawable.ic_login_item_info_13),
                contentDescription = "item_info",
            )
            Text(
                text = "계좌 이체 시 확인용으로 사용되며, 공개되지 않아요",
                style = FestimateTheme.typography.capRegular11,
                color = Gray04,
                textAlign = TextAlign.Center,
            )
        }
        FestimateBasicTextField(
            value = uiState.username,
            onValueChange = {
                updateName(it)
            },
            modifier = modifier.padding(vertical = 12.dp),
            shape = RoundedCornerShape(12.dp),
            placeholder = "이름을 입력해주세요",
            textStyle = FestimateTheme.typography.bodyMedium13,
            textColor = Gray04,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "닉네임 ",
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
        Box(
            modifier = modifier.padding(top = 12.dp, bottom = 4.dp),
        ) {
            FestimateBasicTextField(
                modifier = modifier.pointerInput(Unit) {
                    detectTapGestures { }
                },
                value = uiState.nickname,
                onValueChange = { updateNickname(it) },
                shape = RoundedCornerShape(12.dp),
                placeholder = "닉네임을 입력해주세요",
                textStyle = FestimateTheme.typography.bodyMedium13,
                textColor = Gray04,
            )
            FestimateBasicButton(
                modifier = modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
                    .zIndex(1f)
                    .pointerInput(Unit) {},
                shape = RoundedCornerShape(10.dp),
                text = "중복확인",
                textStyle = FestimateTheme.typography.bodySemibold13,
                clickable = uiState.nicknameValidateResult == NicknameValidateResult.CorrectInput,
                backgroundColor = if (uiState.nicknameValidateResult == NicknameValidateResult.CorrectInput
                ) {
                    MainCoral
                } else {
                    Gray03
                },
                onClick = {
                    checkNicknameDuplicate()
                },
                padding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            )
        }
        Text(
            modifier = modifier.padding(bottom = 6.dp),
            text = nicknameHintText,
            style = FestimateTheme.typography.capRegular11,
            color = nicknameHintColor,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "나이 ",
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
        FestimateBasicTextField(
            modifier = modifier.padding(top = 12.dp, bottom = 4.dp),
            value = uiState.age,
            onValueChange = { updateAge(it) },
            shape = RoundedCornerShape(12.dp),
            placeholder = "나이를 입력해주세요",
            textStyle = FestimateTheme.typography.bodyMedium13,
            textColor = Gray04,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword,
            ),
        )
        Row(
            modifier = modifier.padding(top = 12.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "성별 ",
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
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            FestimateBasicButton(
                modifier = Modifier
                    .weight(0.5f),
                shape = RoundedCornerShape(12.dp),
                text = "여자",
                textColor =
                if (uiState.selectedGender == SelectedGender.Female) {
                    White
                } else {
                    Gray04
                },
                textStyle = FestimateTheme.typography.bodySemibold15,
                clickable = true,
                backgroundColor =
                if (uiState.selectedGender == SelectedGender.Female) {
                    MainCoral
                } else {
                    Gray01
                },
                onClick = {
                    updateGender(SelectedGender.Female)
                },
                padding = PaddingValues(vertical = 15.dp),
            )
            FestimateBasicButton(
                modifier = Modifier
                    .weight(0.5f),
                shape = RoundedCornerShape(12.dp),
                text = "남자",
                textColor =
                if (uiState.selectedGender == SelectedGender.Male) {
                    White
                } else {
                    Gray04
                },
                textStyle = FestimateTheme.typography.bodySemibold15,
                clickable = true,
                backgroundColor =
                if (uiState.selectedGender == SelectedGender.Male) {
                    MainCoral
                } else {
                    Gray01
                },
                onClick = {
                    updateGender(SelectedGender.Male)
                },
                padding = PaddingValues(vertical = 15.dp),
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = modifier.padding(end = 8.dp),
                text = "학교 ",
                style = FestimateTheme.typography.bodySemibold17,
                color = Gray06,
                textAlign = TextAlign.Center,
            )
            Image(
                modifier = modifier.padding(end = 2.dp),
                painter = painterResource(id = R.drawable.ic_login_item_info_13),
                contentDescription = "item_info",
            )
            Text(
                text = "학교 정보를 공개하고 싶지 않으시면 미입력해주세요",
                style = FestimateTheme.typography.capRegular11,
                color = Gray04,
                textAlign = TextAlign.Center,
            )
        }
        FestimateBasicTextField(
            modifier = modifier.padding(top = 12.dp, bottom = 14.dp),
            value = uiState.school,
            onValueChange = { updateSchool(it) },
            shape = RoundedCornerShape(12.dp),
            placeholder = "나이를 입력해주세요",
            textStyle = FestimateTheme.typography.bodyMedium13,
            textColor = Gray04,
        )
    }
}
