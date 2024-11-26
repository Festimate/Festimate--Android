package com.mtc.signup

import android.util.Log
import com.mtc.model.NicknameValidateResult
import com.mtc.signup.Mbti.Companion.toModel
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
                firstUserInfoScreenResult = false,
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

    fun updateHeight(height: String) {
        intent {
            copy(
                height = height,
            )
        }
    }

    fun updateMbti(mbti: Mbti) {
        when (mbti) {
            Mbti.E, Mbti.I -> {
                if (mbti == uiState.value.m) {
                    intent {
                        copy(
                            m = Mbti.Empty,
                        )
                    }
                } else {
                    intent {
                        copy(
                            m = mbti,
                        )
                    }
                }
            }

            Mbti.N, Mbti.S -> {
                if (mbti == uiState.value.b) {
                    intent {
                        copy(
                            b = Mbti.Empty,
                        )
                    }
                } else {
                    intent {
                        copy(
                            b = mbti,
                        )
                    }
                }
            }

            Mbti.F, Mbti.T -> {
                if (mbti == uiState.value.t) {
                    intent {
                        copy(
                            t = Mbti.Empty,
                        )
                    }
                } else {
                    intent {
                        copy(
                            t = mbti,
                        )
                    }
                }
            }

            Mbti.P, Mbti.J -> {
                if (mbti == uiState.value.i) {
                    intent {
                        copy(
                            i = Mbti.Empty,
                        )
                    }
                } else {
                    intent {
                        copy(
                            i = mbti,
                        )
                    }
                }
            }

            else -> {
                intent {
                    copy(
                        m = Mbti.Empty,
                        b = Mbti.Empty,
                        t = Mbti.Empty,
                        i = Mbti.Empty,
                    )
                }
            }
        }
    }

    fun firstUserInfoScreenResultValidate() {
        if (uiState.value.username.isNotBlank() && uiState.value.nicknameValidateResult == NicknameValidateResult.Success &&
            uiState.value.age.isNotBlank() && uiState.value.selectedGender != SelectedGender.Empty && uiState.value.school.isNotBlank()
        ) {
            intent {
                copy(
                    firstUserInfoScreenResult = true,
                )
            }
        }
    }

    fun secondUserInfoScreenResultValidate() {
        if (uiState.value.height.isNotBlank() &&
            uiState.value.m.toModel()?.isNotBlank() == true &&
            uiState.value.b.toModel()?.isNotBlank() == true &&
            uiState.value.t.toModel()?.isNotBlank() == true &&
            uiState.value.i.toModel()?.isNotBlank() == true
        ) {
            intent {
                copy(
                    mbti = uiState.value.run {
                        m.toModel() + b.toModel() + t.toModel() + i.toModel()
                    },
                    secondUserInfoScreenResult = true,
                )
            }
        } else intent {
            copy(
                mbti = uiState.value.run {
                    m.toModel() + b.toModel() + t.toModel() + i.toModel()
                },
                secondUserInfoScreenResult = false,
            )
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
