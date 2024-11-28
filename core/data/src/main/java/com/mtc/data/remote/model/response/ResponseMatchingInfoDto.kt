package com.mtc.data.remote.model.response

import com.mtc.model.MatchingInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMatchingInfoDto(
    @SerialName("matchingId")
    val matchingId: Long,
    @SerialName("location")
    val location: String,
    @SerialName("time")
    val time: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("school")
    val school: String,
    @SerialName("tagList")
    val tagList: List<String>,
    @SerialName("dress")
    val dress: String,
)

fun ResponseMatchingInfoDto.toModel() = MatchingInfo(
    matchingId = matchingId,
    location = location,
    time = time,
    nickname = nickname,
    school = school,
    faceFirst = tagList[0],
    faceSecond = tagList.getOrNull(1),
    dress = dress,
    age = "20",
    mbti = "entp",
)
