package com.mtc.addmatching

import com.mtc.model.IdealTypeInfo
import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddMatchingViewModel @Inject constructor(
) : BaseViewModel<AddMatchingState, AddMatchingSideEffect>(AddMatchingState()) {

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

    fun updateIdealTypeInfo(idealTypeInfo: IdealTypeInfo) {
        if (idealTypeInfo.minAge?.isNotBlank() == true &&
            idealTypeInfo.maxAge?.isNotBlank() == true &&
            idealTypeInfo.minHeight?.isNotBlank() == true &&
            idealTypeInfo.maxHeight?.isNotBlank() == true &&
            idealTypeInfo.mbti?.isNotBlank() == true &&
            idealTypeInfo.apperanceList?.isNotEmpty() == true
        ) {
            intent {
                copy(
                    minAge = idealTypeInfo.minAge!!,
                    maxAge = idealTypeInfo.maxAge!!,
                    minHeight = idealTypeInfo.minHeight!!,
                    maxHeight = idealTypeInfo.maxHeight!!,
                    mbti = idealTypeInfo.mbti!!,
                    appearanceList = idealTypeInfo.apperanceList!!,
                    idealTypeResult = true,
                )
            }
        } else {
            intent {
                copy(
                    idealTypeResult = false,
                )
            }
        }
    }

    fun addNewMatching() {
        postSideEffect(
            AddMatchingSideEffect.Success,
        )
    }
}
