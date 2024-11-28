package com.mtc.model

data class ApiError(
    override val message: String
): Exception(message)
