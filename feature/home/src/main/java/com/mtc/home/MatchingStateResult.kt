package com.mtc.home

sealed interface MatchingStateResult {
    data object Empty : MatchingStateResult
    data object Loading : MatchingStateResult
    data object Success : MatchingStateResult
}
