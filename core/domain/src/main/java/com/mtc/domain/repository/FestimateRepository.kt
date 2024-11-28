package com.mtc.domain.repository

import com.mtc.model.MatchingInfo
import com.mtc.model.RegisterMatching
import com.mtc.model.SignUp
import com.mtc.model.UserDetail

interface FestimateRepository {
    suspend fun postCheckNickname(nickname: String): Result<Unit>
    suspend fun postSignUp(signUp: SignUp): Result<Long>
    suspend fun getUserDetail(userId: Long): Result<UserDetail>
    suspend fun getMatchingList(userId: Long): List<MatchingInfo>
    suspend fun postRegisterMatching(userId: Long, registerMatching: RegisterMatching): Long
}
