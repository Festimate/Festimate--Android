package com.mtc.addmatching

import com.mtc.model.IdealTypeInfo
import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class AddMatchingViewModel @Inject constructor() : BaseViewModel<AddMatchingState, AddMatchingSideEffect>(AddMatchingState()) {

    fun navigateIdealType() {
        postSideEffect(
            AddMatchingSideEffect.IdealType,
        )
    }

    fun navigateDateTaste() {
        postSideEffect(
            AddMatchingSideEffect.DateTaste,
        )
    }

    fun updateIdealTypeInfo(encodeIdealTypeList: String?) {
        if (!encodeIdealTypeList.isNullOrBlank()) {
            val idealTypeList = Json.decodeFromString<IdealTypeInfo>(encodeIdealTypeList)
            idealTypeList.let {
                intent {
                    copy(
                        minAge = it.minAge,
                        maxAge = it.maxAge,
                        minHeight = it.minHeight,
                        maxHeight = it.maxHeight,
                        mbti = it.mbti,
                        appearanceList = it.apperanceList,
                        idealTypeResult = true,
                    )
                }
            }
        } else {
            intent {
                copy(
                    idealTypeResult = false,
                )
            }
        }
    }

    fun updateDateTasteInfo(dateTasteList: List<Int>?) {
        if (dateTasteList != null) {
            intent {
                copy(
                    questionList = dateTasteList,
                    dateTasteResult = true,
                )
            }
        } else {
            intent {
                copy(
                    dateTasteResult = false,
                )
            }
        }
    }

    fun updatePossibleTime(time: String) {
        intent {
            copy(
                timeList = if (uiState.value.timeList.contains(time)) {
                    uiState.value.timeList - time
                } else {
                    uiState.value.timeList + time
                },
            )
        }
    }

    fun updateCloth(cloth: String) {
        intent {
            copy(
                cloth = cloth,
            )
        }
    }

    fun addNewMatching() {
        postSideEffect(
            AddMatchingSideEffect.Success,
        )
    }

    fun updateAddMatchingResultBack() {
        postSideEffect(
            AddMatchingSideEffect.Back,
        )
    }
}
