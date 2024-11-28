package com.mtc.home

import androidx.lifecycle.viewModelScope
import com.mtc.datastore.datastore.SecurityDataStore
import com.mtc.domain.repository.FestimateRepository
import com.mtc.model.MatchingInfo
import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
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
        viewModelScope.launch {
            festimateRepository.getUserDetail(dataStore.flowUserId().first())
                .onSuccess {
                    intent {
                        copy(
                            userNickname = it.nickname,
                            userSchool = it.school,
                        )
                    }
                }.onFailure {
                    postSideEffect(
                        HomeSideEffect.Error,
                    )
                }
        }
    }

    fun getMatchingList() {
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
        viewModelScope.launch {
            festimateRepository.getMatchingList(dataStore.flowUserId().first())
                .onSuccess {
                    intent {
                        copy(
                            matchingStateResult = MatchingStateResult.Success,
                            matchingInfo = it,
                        )
                    }
                }.onFailure {
                    intent {
                        copy(
                            matchingStateResult = MatchingStateResult.Success,
                            matchingInfo = list2,
                            //더미데이터 나중에 수정(지금 빈 리스트날아와서 - 나중에 Empty, list2지워야됨
                            )
                    }
                }
        }
    }

    fun navigateAddMatching() {
        postSideEffect(
            HomeSideEffect.Success,
        )
    }
}
