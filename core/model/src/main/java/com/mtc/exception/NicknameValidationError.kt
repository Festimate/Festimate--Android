package com.mtc.exception

data class NicknameValidationError(
    override val message: String,
) : Exception(message)
