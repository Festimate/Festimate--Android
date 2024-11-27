package com.mtc.datetaste

import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DateTasteViewModel @Inject constructor(
) : BaseViewModel<DateTasteState, DateTasteSideEffect>(DateTasteState()) {

    fun updateDateTasteResult() {
        postSideEffect(
            DateTasteSideEffect.Success,
        )
    }
}
