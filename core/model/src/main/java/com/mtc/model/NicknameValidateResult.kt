package com.mtc.model

interface NicknameValidateResult {
    data object Empty : NicknameValidateResult
    data object KoreanError : NicknameValidateResult
    data object LengthError : NicknameValidateResult
    data object CorrectInput : NicknameValidateResult
    data object Duplicate : NicknameValidateResult
    data object Success : NicknameValidateResult
}
