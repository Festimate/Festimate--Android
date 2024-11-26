package com.mtc.model

import kotlinx.serialization.Serializable

@Serializable
data class IdealTypeInfo(
    val minAge: String?,
    val maxAge: String?,
    val minHeight: String?,
    val maxHeight: String?,
    val mbti: String?,
    val apperanceList: List<String>?,
)
