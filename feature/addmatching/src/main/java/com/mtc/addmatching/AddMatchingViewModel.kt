package com.mtc.addmatching

import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddMatchingViewModel @Inject constructor(
) : BaseViewModel<AddMatchingState, AddMatchingSideEffect>(AddMatchingState()) {

    fun addNewMatching() {
        postSideEffect(
            AddMatchingSideEffect.Success,
        )
    }
}
