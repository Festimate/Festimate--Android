package com.mtc.idealtype

import com.mtc.model.Appearance
import com.mtc.model.Mbti
import com.mtc.ui.base.SideEffect
import com.mtc.ui.base.UiState

data class IdealTypeState(
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
    val firstIdealTypeScreenResult: Boolean = false,
    val secondIdealTypeScreenResult: Boolean = false,
) : UiState

sealed interface IdealTypeSideEffect : SideEffect {
    data object Empty : IdealTypeSideEffect
    data object Error : IdealTypeSideEffect
    data object Loading : IdealTypeSideEffect
    data object Success : IdealTypeSideEffect
}
