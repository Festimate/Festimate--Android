package com.mtc.data.repository

import com.mtc.data.remote.api.FestimateApi
import com.mtc.data.remote.model.request.toDto
import com.mtc.data.remote.model.response.toModel
import com.mtc.domain.repository.FestimateRepository
import com.mtc.model.MatchingInfo
import com.mtc.model.RegisterMatching
import com.mtc.model.SignUp
import com.mtc.model.UserDetail
import javax.inject.Inject

class FestimateRepositoryImpl @Inject constructor(
    private val festimateApi: FestimateApi,
) : FestimateRepository {
    override suspend fun postCheckNickname(nickname: String) {
        festimateApi.postCheckNicknameDuplicate(nickname)
    }

    override suspend fun postSignUp(signUp: SignUp): Long =
        festimateApi.postSignUp(signUp.toDto()).toModel()


    override suspend fun getUserDetail(userId: Long): UserDetail =
        festimateApi.getUserDetail(userId).toModel()


    override suspend fun getMatchingList(userId: Long): List<MatchingInfo> =
        festimateApi.getMatchingList(userId).map {
            it.toModel()
        }

    override suspend fun postRegisterMatching(userId: Long, registerMatching: RegisterMatching): Long =
        festimateApi.postRegisterMatching(userId, registerMatching.toDto()).toModel()
}
