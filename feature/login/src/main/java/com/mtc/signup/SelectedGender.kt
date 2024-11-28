package com.mtc.signup

enum class SelectedGender {
    Empty,
    Female,
    Male,
}

fun SelectedGender.toDto(): String {
    return when (this) {
        SelectedGender.Empty -> "에러"
        SelectedGender.Female -> "여자"
        SelectedGender.Male -> "남자"
    }
}
