package com.mtc.signup

import com.mtc.model.Appearance
import com.mtc.model.Mbti
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
    val height: String = "",
    val m: Mbti = Mbti.Empty,
    val b: Mbti = Mbti.Empty,
    val t: Mbti = Mbti.Empty,
    val i: Mbti = Mbti.Empty,
    val mbti: String = "",
    val firstAppearance: Appearance = Appearance.Empty,
    val secondAppearance: Appearance = Appearance.Empty,
    val apperanceList: List<String> = emptyList(),
    val firstUserInfoScreenResult: Boolean = false,
    val secondUserInfoScreenResult: Boolean = false,
    val thirdUserInfoScreenResult: Boolean = false,
) : UiState

sealed interface SignUpSideEffect : SideEffect {
    data object Empty : SignUpSideEffect
    data object Error : SignUpSideEffect
    data object Loading : SignUpSideEffect
    data object Success : SignUpSideEffect
}
