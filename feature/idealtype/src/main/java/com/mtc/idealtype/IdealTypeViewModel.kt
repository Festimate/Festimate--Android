package com.mtc.idealtype

import com.mtc.model.Appearance
import com.mtc.model.Appearance.Companion.toModel
import com.mtc.model.Mbti
import com.mtc.model.Mbti.Companion.toModel
import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IdealTypeViewModel @Inject constructor() : BaseViewModel<IdealTypeState, IdealTypeSideEffect>(IdealTypeState()) {

    fun updateMinAge(minAge: String) {
        intent {
            copy(
                minAge = minAge,
            )
        }
    }

    fun updateMaxAge(maxAge: String) {
        intent {
            copy(
                maxAge = maxAge,
            )
        }
    }

    fun updateMinHeight(minHeight: String) {
        intent {
            copy(
                minHeight = minHeight,
            )
        }
    }

    fun updateMaxHeight(maxHeight: String) {
        intent {
            copy(
                maxHeight = maxHeight,
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

        val apperanceList = mutableListOf<String>()
        apperanceList.add(uiState.value.firstAppearance.toModel()!!)
        if (uiState.value.secondAppearance.toModel()?.isNotBlank() == true) {
            apperanceList.add(uiState.value.secondAppearance.toModel()!!)
        }
        intent {
            copy(
                appearanceList = apperanceList,
            )
        }
    }

    fun firstIdealTypeScreenResultValidate() {
        if (uiState.value.minAge.isNotBlank() && uiState.value.maxAge.isNotBlank() && uiState.value.minHeight.isNotBlank() &&
            uiState.value.maxHeight.isNotBlank() && uiState.value.m.toModel()?.isNotBlank() == true &&
            uiState.value.b.toModel()?.isNotBlank() == true &&
            uiState.value.t.toModel()?.isNotBlank() == true &&
            uiState.value.i.toModel()?.isNotBlank() == true
        ) {
            intent {
                copy(
                    mbti = uiState.value.run {
                        m.toModel() + b.toModel() + t.toModel() + i.toModel()
                    },
                    firstIdealTypeScreenResult = true,
                )
            }
        } else {
            intent {
                copy(
                    mbti = uiState.value.run {
                        m.toModel() + b.toModel() + t.toModel() + i.toModel()
                    },
                    firstIdealTypeScreenResult = false,
                )
            }
        }
    }

    fun secondIdealTypeScreenResultValidate() {
        if (uiState.value.firstAppearance.toModel()?.isNotBlank() == true) {
            intent {
                copy(
                    secondIdealTypeScreenResult = true,
                )
            }
        } else {
            intent {
                copy(
                    secondIdealTypeScreenResult = false,
                )
            }
        }
    }

    fun updateIdealTypeResult() {
        postSideEffect(
            IdealTypeSideEffect.Success,
        )
    }
}
