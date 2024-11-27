package com.mtc.datetaste

import com.mtc.ui.base.SideEffect
import com.mtc.ui.base.UiState

data class DateTasteState(
    val firstQuestion: Int = 0,
    val secondQuestion: Int = 0,
    val thirdQuestion: Int = 0,
    val fourthQuestion: Int = 0,
    val fifthQuestion: Int = 0,
    val dateTasteList: List<Int> = emptyList(),
    val dateTasteScreenResult: Boolean = false,
) : UiState

sealed interface DateTasteSideEffect : SideEffect {
    data object Back : DateTasteSideEffect
    data object Empty : DateTasteSideEffect
    data object Error : DateTasteSideEffect
    data object Loading : DateTasteSideEffect
    data object Success : DateTasteSideEffect
}
