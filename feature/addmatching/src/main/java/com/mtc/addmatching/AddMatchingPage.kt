package com.mtc.addmatching

enum class AddMatchingPage {
    Error,
    FirstAddMatching,
    SecondAddMatching,
    ThirdAddMatching,
    FourthAddMatching,
    
    ;

    companion object {
        fun Int.toAddMatching(): AddMatchingPage {
            when (this) {
                0 -> return FirstAddMatching
                1 -> return SecondAddMatching
                2 -> return ThirdAddMatching
                3 -> return FourthAddMatching
            }
            return Error
        }
    }
}
