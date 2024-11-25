package com.mtc.signup

import android.util.Log
import com.mtc.model.NicknameValidateResult
import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : BaseViewModel<SignUpState, SignUpSideEffect>(SignUpState()) {

    fun updateName(name: String) {
        intent {
            copy(
                username = name,
            )
        }
    }

    fun updateNickName(nickname: String) {
        intent {
            copy(
                nickname = nickname,
                nicknameValidateResult = checkNicknameValidate(nickname),
            )
        }
    }

    fun checkNickNameDuplicate() {
        if (uiState.value.nickname != "아아") {
            intent {
                Log.d("asdasd", "not duplicate")
                copy(
                    nicknameValidateResult = NicknameValidateResult.Success,
                )
            }
        }
    }

    fun updateAge(age: String) {
        intent {
            copy(
                age = age,
            )
        }
    }

    fun updateGender(gender: SelectedGender) {
        if (gender != uiState.value.selectedGender) {
            intent {
                copy(
                    selectedGender = gender,
                )
            }
        } else if (gender == uiState.value.selectedGender) {
            intent {
                copy(
                    selectedGender = SelectedGender.Empty,
                )
            }
        }
    }

    fun updateSchool(school: String) {
        intent {
            copy(
                school = school,
            )
        }
    }

    fun nameScreenResultValidate() {
        if (uiState.value.username.isNotBlank() && uiState.value.nicknameValidateResult == NicknameValidateResult.Success &&
            uiState.value.age.isNotBlank() && uiState.value.selectedGender != SelectedGender.Empty && uiState.value.school.isNotBlank()
        ) {
            Log.d("asdasd", "next")
            intent {
                copy(
                    nameScreenResult = true,
                )
            }
        }
    }

    fun signUp() {
        postSideEffect(
            SignUpSideEffect.Success,
        )
    }

    private fun checkNicknameValidate(nickname: String): NicknameValidateResult {
        return when {
            nickname.isEmpty() || nickname.length == 1 -> NicknameValidateResult.Empty
            nickname.all { it !in '가'..'힣' } -> NicknameValidateResult.KoreanError
            nickname.length !in 1..10 -> NicknameValidateResult.LengthError
            else -> NicknameValidateResult.CorrectInput
        }
    }
}
