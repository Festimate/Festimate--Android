package com.mtc.data.remote.api

import com.mtc.data.remote.model.request.RequestRegisterMatchingDto
import com.mtc.data.remote.model.request.RequestSignUpDto
import com.mtc.data.remote.model.response.ResponseMatchingIdDto
import com.mtc.data.remote.model.response.ResponseMatchingInfoDto
import com.mtc.data.remote.model.response.ResponseUserDetailDto
import com.mtc.data.remote.model.response.ResponseUserIdDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface FestimateApi {

    @POST("festimate/v1/users/check-nickname")
    suspend fun postCheckNicknameDuplicate(
        @Query("nickname") nickname: String,
    )

    @POST("festimate/v1/signup")
    suspend fun postSignUp(
        @Body requestSignUpDto: RequestSignUpDto,
    ): ResponseUserIdDto

    @GET("festimate/v1/user-detail")
    suspend fun getUserDetail(
        @Header("userId") userId: Long,
    ): ResponseUserDetailDto

    @GET("festimate/v1/matching")
    suspend fun getMatchingList(
        @Header("userId") userId: Long,
    ): List<ResponseMatchingInfoDto>

    @POST("festimate/v1/matching")
    suspend fun postRegisterMatching(
        @Header("userId") userId: Long,
        @Body requestRegisterMatchingDto: RequestRegisterMatchingDto,
    ): ResponseMatchingIdDto
}
