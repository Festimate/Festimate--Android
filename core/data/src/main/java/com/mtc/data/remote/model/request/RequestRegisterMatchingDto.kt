package com.mtc.data.remote.model.request

import com.mtc.model.RegisterMatching
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestRegisterMatchingDto(
    @SerialName("minHeight")
    val minHeight: Int,
    @SerialName("maxHeight")
    val maxHeight: Int,
    @SerialName("minAge")
    val minAge: Int,
    @SerialName("maxAge")
    val maxAge: Int,
    @SerialName("mbti")
    val mbti: String,
    @SerialName("appearanceList")
    val appearanceList: List<String>,
    @SerialName("questionList")
    val questionList: List<Int>,
    @SerialName("timeList")
    val timeList: List<String>,
    @SerialName("dress")
    val dress: String,
)

fun RegisterMatching.toDto() = RequestRegisterMatchingDto(
    minHeight = minHeight,
    maxHeight = maxHeight,
    minAge = minAge,
    maxAge = maxAge,
    mbti = mbti,
    appearanceList = appearanceList,
    questionList = questionList,
    timeList = timeList,
    dress = dress,
)
