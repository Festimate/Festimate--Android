package com.mtc.datetaste

import com.mtc.ui.base.SideEffect
import com.mtc.ui.base.UiState

data class DateTasteState(
    val dateTasteList: List<String> = emptyList(),
) : UiState

sealed interface DateTasteSideEffect : SideEffect {
    data object Empty : DateTasteSideEffect
    data object Error : DateTasteSideEffect
    data object Loading : DateTasteSideEffect
    data object Success : DateTasteSideEffect
}
