package com.mtc.idealtype

enum class IdealTypePage {
    Error,
    FirstIdealType,
    SecondIdealType,
    ThirdIdealType,
    ;

    companion object {
        fun Int.toSignupPager(): IdealTypePage {
            when (this) {
                0 -> return FirstIdealType
                1 -> return SecondIdealType
                2 -> return ThirdIdealType
            }
            return Error
        }
    }
}
