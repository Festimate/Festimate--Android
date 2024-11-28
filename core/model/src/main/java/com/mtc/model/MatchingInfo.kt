package com.mtc.model

data class MatchingInfo(
    val matchingId: Long,
    val location: String,
    val time: String,
    val nickname: String,
    val school: String,
    val age: String,
    val mbti: String,
    val faceFirst: String,
    val faceSecond: String? = null,
    val dress: String,
)
