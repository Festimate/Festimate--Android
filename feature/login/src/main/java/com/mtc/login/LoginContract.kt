package com.mtc.login

import com.mtc.ui.base.SideEffect
import com.mtc.ui.base.UiState

data class LoginState(
    val existAccount: Boolean = false,
) : UiState

sealed interface LoginSideEffect : SideEffect {
    data object Empty : LoginSideEffect
    data object Loading : LoginSideEffect
    data object Failure : LoginSideEffect
    data object Success : LoginSideEffect
}
