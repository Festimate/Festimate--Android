package com.mtc.idealtype

enum class IdealTypePage {
    Error,
    FirstIdealType,
    SecondIdealType,
    ;

    companion object {
        fun Int.toIdealTypePage(): IdealTypePage {
            when (this) {
                0 -> return FirstIdealType
                1 -> return SecondIdealType
            }
            return Error
        }
    }
}
