package com.mtc.data.remote.model.response

import com.mtc.model.UserDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserDetailDto(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("school")
    val school: String,
)

fun ResponseUserDetailDto.toModel() = UserDetail(
    nickname = nickname,
    school = school,
)
