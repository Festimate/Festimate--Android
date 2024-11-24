package com.mtc.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mtc.designsystem.component.FestimateTopAppBar

@Composable
fun HeightScreen(
    modifier: Modifier = Modifier,
    uiState: SignUpState,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        FestimateTopAppBar(
            signupPageNumber = "2",
            signupPageContent = "내 정보 입력",
        )

    }
}
