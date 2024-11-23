package com.mtc.model

data class MatchingInfo(
    val location: String,
    val time: String,
    val nickname: String,
    val university: String,
    val age: String,
    val mbti: String,
    val faceFirst: String,
    val faceSecond: String? = null,
    val todayCloth: String,
)
