package com.mtc.datetaste

enum class DateTastePage {
    Error,
    FirstDateTaste,
    SecondDateTaste,
    ThirdDateTaste,
    FourthDateTaste,
    FifthDateTaste
    ;

    companion object {
        fun Int.toDateTastePage(): DateTastePage {
            when (this) {
                0 -> return FirstDateTaste
                1 -> return SecondDateTaste
                2 -> return ThirdDateTaste
                3 -> return FourthDateTaste
                4 -> return FifthDateTaste
            }
            return Error
        }
    }
}
