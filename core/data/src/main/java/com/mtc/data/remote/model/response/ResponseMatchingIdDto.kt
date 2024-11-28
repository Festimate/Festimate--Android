package com.mtc.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMatchingIdDto(
    @SerialName("appliedMatchingId")
    val appliedMatchingId: Long,
)

fun ResponseMatchingIdDto.toModel() =
    appliedMatchingId
