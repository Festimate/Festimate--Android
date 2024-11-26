package com.mtc.signup

enum class SignUpPage {
    Error,
    FirstUserInfo,
    SecondUserInfo,
    ThirdUserInfo,
    ;

    companion object {
        fun Int.toSignupPager(): SignUpPage {
            when (this) {
                0 -> return FirstUserInfo
                1 -> return SecondUserInfo
                2 -> return ThirdUserInfo
            }
            return Error
        }
    }
}
