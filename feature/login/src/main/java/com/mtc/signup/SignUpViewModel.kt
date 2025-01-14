package com.mtc.signup

import androidx.lifecycle.viewModelScope
import com.mtc.datastore.datastore.SecurityDataStore
import com.mtc.domain.repository.FestimateRepository
import com.mtc.exception.NicknameValidationError
import com.mtc.model.Appearance
import com.mtc.model.Appearance.Companion.toModel
import com.mtc.model.Mbti
import com.mtc.model.Mbti.Companion.toModel
import com.mtc.model.NicknameValidateResult
import com.mtc.model.SignUp
import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val dataStore: SecurityDataStore,
    private val festimateRepository: FestimateRepository,
) : BaseViewModel<SignUpState, SignUpSideEffect>(SignUpState()) {

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
        viewModelScope.launch {
            festimateRepository.postCheckNickname(uiState.value.nickname)
                .onSuccess {
                    intent {
                        copy(
                            nicknameValidateResult = NicknameValidateResult.Success,
                        )
                    }
                }.onFailure { exception ->
                    when (exception) {
                        is NicknameValidationError -> {
                            intent {
                                copy(
                                    nicknameValidateResult = NicknameValidateResult.Duplicate,
                                )
                            }
                        }

                        else -> {
                            intent {
                                copy(
                                    nicknameValidateResult = NicknameValidateResult.Error,
                                )
                            }
                        }
                    }
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

    fun updateAppearance(appearance: Appearance) {
        if (appearance == uiState.value.firstAppearance) {
            intent {
                copy(
                    firstAppearance = uiState.value.secondAppearance,
                    secondAppearance = Appearance.Empty,
                )
            }
        } else if (appearance == uiState.value.secondAppearance) {
            intent {
                copy(
                    secondAppearance = Appearance.Empty,
                )
            }
        } else {
            if (uiState.value.firstAppearance == Appearance.Empty) {
                intent {
                    copy(
                        firstAppearance = appearance,
                    )
                }
            } else if (uiState.value.secondAppearance == Appearance.Empty) {
                intent {
                    copy(
                        secondAppearance = appearance,
                    )
                }
            } else {
                intent {
                    copy(
                        firstAppearance = uiState.value.secondAppearance,
                        secondAppearance = appearance,
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
        } else {
            intent {
                copy(
                    mbti = uiState.value.run {
                        m.toModel() + b.toModel() + t.toModel() + i.toModel()
                    },
                    secondUserInfoScreenResult = false,
                )
            }
        }
    }

    fun thirdUserInfoScreenResultValidate() {
        if (uiState.value.firstAppearance.toModel()?.isNotBlank() == true) {
            intent {
                copy(
                    apperanceList = buildList {
                        add(uiState.value.firstAppearance.toModel().toString())
                        if (uiState.value.secondAppearance != Appearance.Empty) {
                            add(uiState.value.secondAppearance.toModel().toString())
                        }
                    },
                    thirdUserInfoScreenResult = true,
                )
            }
        } else {
            intent {
                copy(
                    thirdUserInfoScreenResult = false,
                )
            }
        }
    }

    fun signUp() {
        viewModelScope.launch {
            festimateRepository.postSignUp(
                SignUp(
                    username = uiState.value.username,
                    nickname = uiState.value.nickname,
                    age = uiState.value.age.toInt(),
                    gender = uiState.value.selectedGender.toDto(),
                    school = uiState.value.school,
                    height = uiState.value.height.toInt(),
                    mbti = uiState.value.mbti,
                    appearanceList = uiState.value.apperanceList,
                ),
            ).onSuccess {
                saveUserId(it)
            }.onFailure {
                postSideEffect(
                    SignUpSideEffect.Error,
                )
            }
        }
    }

    private fun saveUserId(userId: Long) {
        viewModelScope.launch {
            runCatching {
                dataStore.setUserId(userId)
            }.onSuccess {
                postSideEffect(
                    SignUpSideEffect.Success,
                )
            }.onFailure {
                postSideEffect(
                    SignUpSideEffect.Error,
                )
            }
        }
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
