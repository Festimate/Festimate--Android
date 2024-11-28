package com.mtc.addmatching

import androidx.lifecycle.viewModelScope
import com.mtc.datastore.datastore.SecurityDataStore
import com.mtc.domain.repository.FestimateRepository
import com.mtc.model.IdealTypeInfo
import com.mtc.model.RegisterMatching
import com.mtc.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class AddMatchingViewModel @Inject constructor(
    private val dataStore: SecurityDataStore,
    private val festimateRepository: FestimateRepository,
) : BaseViewModel<AddMatchingState, AddMatchingSideEffect>(AddMatchingState()) {

    fun navigateIdealType() {
        postSideEffect(
            AddMatchingSideEffect.IdealType,
        )
    }

    fun navigateDateTaste() {
        postSideEffect(
            AddMatchingSideEffect.DateTaste,
        )
    }

    fun updateIdealTypeInfo(encodeIdealTypeList: String?) {
        if (!encodeIdealTypeList.isNullOrBlank()) {
            val idealTypeList = Json.decodeFromString<IdealTypeInfo>(encodeIdealTypeList)
            idealTypeList.let {
                intent {
                    copy(
                        minAge = it.minAge,
                        maxAge = it.maxAge,
                        minHeight = it.minHeight,
                        maxHeight = it.maxHeight,
                        mbti = it.mbti,
                        appearanceList = it.apperanceList,
                        idealTypeResult = true,
                    )
                }
            }
        } else {
            intent {
                copy(
                    idealTypeResult = false,
                )
            }
        }
    }

    fun updateDateTasteInfo(dateTasteList: List<Int>?) {
        if (dateTasteList != null) {
            intent {
                copy(
                    questionList = dateTasteList,
                    dateTasteResult = true,
                )
            }
        } else {
            intent {
                copy(
                    dateTasteResult = false,
                )
            }
        }
    }

    fun updatePossibleTime(time: String) {
        intent {
            copy(
                timeList = if (uiState.value.timeList.contains(time)) {
                    uiState.value.timeList - time
                } else {
                    uiState.value.timeList + time
                },
            )
        }
    }

    fun updateCloth(cloth: String) {
        intent {
            copy(
                dress = cloth,
            )
        }
    }

    fun getAccount() {
        intent {
            copy(
                bankName = "카카오뱅크",
                bankAccount = "3333-18-1234567",
            )
        }
    }

    fun addNewMatching() {
        viewModelScope.launch {
            festimateRepository.postRegisterMatching(
                dataStore.flowUserId().first(),
                RegisterMatching(
                    minHeight = uiState.value.minHeight.toInt(),
                    maxHeight = uiState.value.maxHeight.toInt(),
                    minAge = uiState.value.minAge.toInt(),
                    maxAge = uiState.value.maxAge.toInt(),
                    mbti = uiState.value.mbti,
                    appearanceList = uiState.value.appearanceList,
                    questionList = uiState.value.questionList,
                    timeList = uiState.value.timeList,
                    dress = uiState.value.dress,
                ),
            ).onSuccess {
                postSideEffect(
                    AddMatchingSideEffect.Success,
                )
            }.onFailure {
                postSideEffect(
                    AddMatchingSideEffect.Error,
                )
            }
        }
    }

    fun updateAddMatchingResultBack() {
        postSideEffect(
            AddMatchingSideEffect.Back,
        )
    }
}
