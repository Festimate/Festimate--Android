package com.mtc.addmatching

import com.mtc.model.Appearance
import com.mtc.model.Mbti
import com.mtc.ui.base.SideEffect
import com.mtc.ui.base.UiState

data class AddMatchingState(
    val minAge: String = "",
    val maxAge: String = "",
    val minHeight: String = "",
    val maxHeight: String = "",
    val m: Mbti = Mbti.Empty,
    val b: Mbti = Mbti.Empty,
    val t: Mbti = Mbti.Empty,
    val i: Mbti = Mbti.Empty,
    val mbti: String = "",
    val firstAppearance: Appearance = Appearance.Empty,
    val secondAppearance: Appearance = Appearance.Empty,
    val questionList: List<String> = emptyList(),
    val timeList: List<String> = emptyList(),
    val dress: String = "",
) : UiState

sealed interface AddMatchingSideEffect : SideEffect {
    data object Empty : AddMatchingSideEffect
    data object Error : AddMatchingSideEffect
    data object Loading : AddMatchingSideEffect
    data object Success : AddMatchingSideEffect
}
