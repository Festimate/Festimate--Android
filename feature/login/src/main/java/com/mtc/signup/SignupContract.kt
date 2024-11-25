package com.mtc.signup

import com.mtc.model.NicknameValidateResult
import com.mtc.ui.base.SideEffect
import com.mtc.ui.base.UiState

data class SignUpState(
    val username: String = "",
    val nickname: String = "",
    val nicknameValidateResult: NicknameValidateResult = NicknameValidateResult.Empty,
    val age: String = "",
    val selectedGender: SelectedGender = SelectedGender.Empty,
    val school: String = "",
    val height: Int = 0,
    val mbti: String = "",
    val apperanceList: List<String> = emptyList(),
    val nameScreenResult: Boolean = false,
    val heightScreenResult: Boolean = false,
    val appearanceScreenResult: Boolean = false,

) : UiState

sealed interface SignUpSideEffect : SideEffect {
    data object Empty : SignUpSideEffect
    data object Error : SignUpSideEffect
    data object Loading : SignUpSideEffect
    data object Success : SignUpSideEffect
}
