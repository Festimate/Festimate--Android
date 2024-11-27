package com.mtc.datetaste

import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DateTasteViewModel @Inject constructor() : BaseViewModel<DateTasteState, DateTasteSideEffect>(DateTasteState()) {

    fun updateFirstDateTasteQuestion(answer: Int) {
        if (uiState.value.firstQuestion == answer) {
            intent {
                copy(
                    firstQuestion = 0,
                )
            }
        } else {
            intent {
                copy(
                    firstQuestion = answer,
                )
            }
        }
    }

    fun updateSecondDateTasteQuestion(answer: Int) {
        if (uiState.value.secondQuestion == answer) {
            intent {
                copy(
                    secondQuestion = 0,
                )
            }
        } else {
            intent {
                copy(
                    secondQuestion = answer,
                )
            }
        }
    }

    fun updateThirdDateTasteQuestion(answer: Int) {
        if (uiState.value.thirdQuestion == answer) {
            intent {
                copy(
                    thirdQuestion = 0,
                )
            }
        } else {
            intent {
                copy(
                    thirdQuestion = answer,
                )
            }
        }
    }

    fun updateFourthDateTasteQuestion(answer: Int) {
        if (uiState.value.fourthQuestion == answer) {
            intent {
                copy(
                    fourthQuestion = 0,
                )
            }
        } else {
            intent {
                copy(
                    fourthQuestion = answer,
                )
            }
        }
    }

    fun updateFifthDateTasteQuestion(answer: Int) {
        if (uiState.value.fifthQuestion == answer) {
            intent {
                copy(
                    fifthQuestion = 0,
                )
            }
        } else {
            intent {
                copy(
                    fifthQuestion = answer,
                )
            }
        }
    }

    fun dateTasteScreenValidate() {
        if (uiState.value.firstQuestion != 0 && uiState.value.secondQuestion != 0 && uiState.value.thirdQuestion != 0 &&
            uiState.value.fourthQuestion != 0 && uiState.value.fifthQuestion != 0
        ) {
            intent {
                copy(
                    dateTasteList = listOf(
                        uiState.value.firstQuestion,
                        uiState.value.secondQuestion,
                        uiState.value.thirdQuestion,
                        uiState.value.fourthQuestion,
                        uiState.value.fifthQuestion,
                    ),
                    dateTasteScreenResult = true,
                )
            }
        } else {
            intent {
                copy(
                    dateTasteScreenResult = false,
                )
            }
        }
    }

    fun updateDateTasteResult() {
        postSideEffect(
            DateTasteSideEffect.Success,
        )
    }
}
