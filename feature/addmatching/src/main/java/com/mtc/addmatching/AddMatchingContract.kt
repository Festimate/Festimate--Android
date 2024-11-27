package com.mtc.addmatching

import com.mtc.ui.base.SideEffect
import com.mtc.ui.base.UiState

data class AddMatchingState(
    val minAge: String = "",
    val maxAge: String = "",
    val minHeight: String = "",
    val maxHeight: String = "",
    val mbti: String = "",
    val appearanceList: List<String> = emptyList(),
    val questionList: List<Int> = emptyList(),
    val timeList: List<String> = emptyList(),
    val dress: String = "",
    val idealTypeResult: Boolean = false,
    val dateTasteResult: Boolean = false,
    val secondAddMatchingScreenResult: Boolean = false,
    val thirdAddMatchingScreenResult: Boolean = false,
    val fourthAddMatchingScreenResult: Boolean = false,
) : UiState

sealed interface AddMatchingSideEffect : SideEffect {
    data object Back : AddMatchingSideEffect
    data object Empty : AddMatchingSideEffect
    data object Error : AddMatchingSideEffect
    data object Loading : AddMatchingSideEffect
    data object Success : AddMatchingSideEffect
    data object IdealType : AddMatchingSideEffect
    data object DateTaste : AddMatchingSideEffect
}
