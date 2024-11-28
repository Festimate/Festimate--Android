package com.mtc.home

import androidx.lifecycle.viewModelScope
import com.mtc.datastore.datastore.SecurityDataStore
import com.mtc.domain.repository.FestimateRepository
import com.mtc.model.MatchingInfo
import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStore: SecurityDataStore,
    private val festimateRepository: FestimateRepository,
) : BaseViewModel<HomeState, HomeSideEffect>(HomeState()) {
    
    fun setAutoSignIn() {
        viewModelScope.launch {
            dataStore.setExistAccount(true)
        }
    }

    fun getUserInfo() {
        intent {
            copy(
                userNickname = "이석찬",
                userSchool = "가톨릭대학교",
            )
        }
    }

    fun getMatchingList() {
        val list1: List<MatchingInfo> = emptyList()
        val list2 = listOf(
            MatchingInfo(
                matchingId = 1,
                location = "스머프 동산",
                time = "20:00 PM",
                nickname = "쿠키",
                school = "가톨릭대학교",
                age = "25세",
                mbti = "INFP",
                faceFirst = "강아지상",
                faceSecond = "두부상",
                dress = "폴로 셔츠에 가디건을 입고 있어요.",
            ),
            MatchingInfo(
                matchingId = 1,
                location = "스머프 동산",
                time = "09:00 PM",
                nickname = "킼",
                school = "가톨릭대학교",
                age = "21세",
                mbti = "INFP",
                faceFirst = "두부상",
                dress = "ㅎㅎㅎㅎ",
            ),
            MatchingInfo(
                matchingId = 1,
                location = "스머프 동산",
                time = "12:00 PM",
                nickname = "우상욱",
                school = "가톨릭대학교",
                age = "19세",
                mbti = "INFP",
                faceFirst = "두부상",
                dress = "ㅎㅎㅎㅎ",
            ),
        )
        intent {
            copy(
                matchingStateResult = MatchingStateResult.Success,
                matchingInfo = list2,
            )
        }
    }

    fun navigateAddMatching() {
        postSideEffect(
            HomeSideEffect.Success,
        )
    }
}
