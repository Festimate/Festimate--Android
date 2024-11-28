package com.mtc.home

import com.mtc.model.MatchingInfo
import com.mtc.ui.base.SideEffect
import com.mtc.ui.base.UiState

data class HomeState(
    val userNickname: String = "",
    val userSchool: String = "",
    val matchingId: Long = 0,
    val location: String = "",
    val time: String = "",
    val matchingNickname: String = "",
    val matchingSchool: String = "",
    val tagList: List<String> = emptyList(),
    val dress: String = "",
    val matchingStateResult: MatchingStateResult = MatchingStateResult.Empty,
    val matchingInfo: List<MatchingInfo> = emptyList(),
) : UiState

sealed interface HomeSideEffect : SideEffect {
    data object Error : HomeSideEffect
    data object Empty : HomeSideEffect
    data object Loading : HomeSideEffect
    data object Success : HomeSideEffect
}
