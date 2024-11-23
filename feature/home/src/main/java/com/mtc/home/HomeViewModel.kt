package com.mtc.home

import com.mtc.model.MatchingInfo
import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeState, HomeSideEffect>(HomeState()) {
    val list1: List<MatchingInfo> = emptyList()
    val list2 = listOf(
        MatchingInfo(
            location = "스머프 동산",
            time = "20:00 PM",
            nickname = "쿠키",
            university = "가톨릭대학교",
            age = "25세",
            mbti = "INFP",
            faceFirst = "강아지상",
            faceSecond = "두부상",
            todayCloth = "폴로 셔츠에 가디건을 입고 있어요.",
        ),
        MatchingInfo(
            location = "스머프 동산",
            time = "09:00 PM",
            nickname = "킼",
            university = "가톨릭대학교",
            age = "21세",
            mbti = "INFP",
            faceFirst = "두부상",
            todayCloth = "ㅎㅎㅎㅎ",
        ),
        MatchingInfo(
            location = "스머프 동산",
            time = "12:00 PM",
            nickname = "우상욱",
            university = "가톨릭대학교",
            age = "19세",
            mbti = "INFP",
            faceFirst = "두부상",
            todayCloth = "ㅎㅎㅎㅎ",
        ),
    )

    fun updateMatchingList() {
        intent {
            copy(
                matchingStateResult = MatchingStateResult.Success,
                matchingInfo = list2,
            )
        }
    }
}