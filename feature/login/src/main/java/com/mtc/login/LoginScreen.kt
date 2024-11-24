package com.mtc.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mtc.designsystem.R
import com.mtc.designsystem.theme.FestimateTheme.typography
import com.mtc.designsystem.theme.Gray06
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginRoute(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.apply {
            getAccountExist()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                LoginSideEffect.Empty -> {}
                LoginSideEffect.Failure -> navigateToSignUp()
                LoginSideEffect.Loading -> {}
                LoginSideEffect.Success -> navigateToHome()
            }
        }
    }

    LoginScreen()
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = modifier.weight(0.38f))
        Text(
            modifier = modifier
                .padding(bottom = 40.dp)
                .fillMaxWidth(),
            text = "축제에서 이상형 찾기!\n 내 취향에 맞는 매칭 서비!",
            textAlign = TextAlign.Center,
            style = typography.bodySemibold17,
            color = Gray06,
        )
        Image(
            painter = painterResource(id = R.drawable.ic_festimate_logo),
            contentDescription = "AppLogo",
        )
        Spacer(modifier = modifier.weight(0.62f))
    }
}
