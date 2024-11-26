package com.mtc.signup

enum class Mbti {
    Empty,
    E,
    I,
    N,
    S,
    F,
    T,
    P,
    J,
    ;

    companion object {
        fun Int.toMbti(): Mbti {
            return when (this) {
                0 -> E
                1 -> I
                2 -> N
                3 -> S
                4 -> F
                5 -> T
                6 -> P
                7 -> J
                else -> Empty
            }
        }

        fun Mbti.toModel(): String? {
            return when (this) {
                E -> "E"
                I -> "I"
                N -> "N"
                S -> "S"
                F -> "F"
                T -> "T"
                P -> "P"
                J -> "J"
                Empty -> null
            }
        }
    }
}
