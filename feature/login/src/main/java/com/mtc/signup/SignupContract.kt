package com.mtc.signup

import com.mtc.ui.base.SideEffect
import com.mtc.ui.base.UiState

data class SignUpState(
    val username: String = "",
    val nickname: String = "",
    val age: Int = 0,
    val gender: String = "",
    val school: String = "",
    val height: Int = 0,
    val mbti: String = "",
    val apperanceList: List<String> = emptyList(),
) : UiState

sealed interface SignUpSideEffect : SideEffect {
    data object Empty : SignUpSideEffect
    data object Error : SignUpSideEffect
    data object Loading : SignUpSideEffect
    data object Success : SignUpSideEffect
}
