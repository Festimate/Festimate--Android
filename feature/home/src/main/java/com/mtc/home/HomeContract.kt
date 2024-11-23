package com.mtc.home

import com.mtc.model.MatchingInfo
import com.mtc.ui.base.SideEffect
import com.mtc.ui.base.UiState

data class HomeState(
    val matchingStateResult: MatchingStateResult = MatchingStateResult.Empty,
    val matchingInfo: List<MatchingInfo> = emptyList(),
) : UiState

sealed interface HomeSideEffect : SideEffect {
    data object Empty : HomeSideEffect
    data object Loading : HomeSideEffect
    data object Success : HomeSideEffect
}
