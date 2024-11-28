package com.mtc.data.remote.model.request

import com.mtc.model.SignUp
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    @SerialName("username")
    val username: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("age")
    val age: Int,
    @SerialName("gender")
    val gender: String,
    @SerialName("school")
    val school: String,
    @SerialName("height")
    val height: Int,
    @SerialName("mbti")
    val mbti: String,
    @SerialName("appearanceList")
    val appearanceList: List<String>,
)

fun SignUp.toDto() = RequestSignUpDto(
    username = username,
    nickname = nickname,
    age = age,
    gender = gender,
    school = school,
    height = height,
    mbti = mbti,
    appearanceList = appearanceList,
)
