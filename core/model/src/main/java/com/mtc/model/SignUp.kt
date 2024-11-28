package com.mtc.model

data class SignUp(
    val username: String,
    val nickname: String,
    val age: Int,
    val gender: String,
    val school: String,
    val height: Int,
    val mbti: String,
    val appearanceList: List<String>,
)
