package com.mtc.signup

enum class SignUpPage {
    Error,
    Name,
    Height,
    Appearance,
    ;

    companion object {
        fun Int.toSignupPager(): SignUpPage {
            when (this) {
                0 -> return Name
                1 -> return Height
                2 -> return Appearance
            }
            return Error
        }
    }
}
