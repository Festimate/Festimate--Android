package com.mtc.data.repository

import com.mtc.data.remote.api.FestimateApi
import com.mtc.data.remote.model.request.toDto
import com.mtc.data.remote.model.response.toModel
import com.mtc.domain.repository.FestimateRepository
import com.mtc.exception.NicknameValidationError
import com.mtc.model.ApiError
import com.mtc.model.MatchingInfo
import com.mtc.model.RegisterMatching
import com.mtc.model.SignUp
import com.mtc.model.UserDetail
import retrofit2.HttpException
import javax.inject.Inject

class FestimateRepositoryImpl @Inject constructor(
    private val festimateApi: FestimateApi,
) : FestimateRepository {
    override suspend fun postCheckNickname(nickname: String): Result<Unit> = runCatching {
        festimateApi.postCheckNicknameDuplicate(nickname)
    }.recoverCatching { exception ->
        when (exception) {
            is HttpException -> {
                if (exception.code() == 400) {
                    throw NicknameValidationError("닉네임 중복 오류")
                } else {
                    throw ApiError("그외 오류")
                }
            }

            else -> {
                throw ApiError("잘몰라")
            }
        }
    }

    override suspend fun postSignUp(signUp: SignUp): Result<Long> = runCatching {
        festimateApi.postSignUp(signUp.toDto())
    }.mapCatching {
        it.toModel()
    }.recoverCatching { exception ->
        throw ApiError(exception.message.toString())
    }

    override suspend fun getUserDetail(userId: Long): Result<UserDetail> = runCatching {
        festimateApi.getUserDetail(userId)
    }.mapCatching {
        it.toModel()
    }.recoverCatching { exception ->
        throw ApiError(exception.message.toString())
    }

    override suspend fun getMatchingList(userId: Long): Result<List<MatchingInfo>> = runCatching {
        festimateApi.getMatchingList(userId)
    }.mapCatching { matchingInfoList ->
        matchingInfoList.map {
            it.toModel()
        }
    }.recoverCatching { exception ->
        throw ApiError(exception.message.toString())
    }

    override suspend fun postRegisterMatching(userId: Long, registerMatching: RegisterMatching): Result<Long> = runCatching {
        festimateApi.postRegisterMatching(userId, registerMatching.toDto())
    }.mapCatching {
        it.toModel()
    }.recoverCatching { exception ->
        throw ApiError(exception.message.toString())
    }
}
