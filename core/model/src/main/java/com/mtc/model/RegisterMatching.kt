package com.mtc.model

data class RegisterMatching(
    val minHeight: Int,
    val maxHeight: Int,
    val minAge: Int,
    val maxAge: Int,
    val mbti: String,
    val appearanceList: List<String>,
    val questionList: List<Int>,
    val timeList: List<String>,
    val dress: String,
)
