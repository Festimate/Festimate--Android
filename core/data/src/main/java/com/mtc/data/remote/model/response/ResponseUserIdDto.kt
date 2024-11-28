package com.mtc.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserIdDto(
    @SerialName("userId")
    val userId: Long,
)

fun ResponseUserIdDto.toModel(): Long =
    userId

