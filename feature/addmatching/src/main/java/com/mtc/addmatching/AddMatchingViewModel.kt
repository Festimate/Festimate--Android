package com.mtc.addmatching

import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun addNewMatching() {
        postSideEffect(
            AddMatchingSideEffect.Success,
        )
    }
}
