package com.mtc.model

enum class Appearance {
    Empty,
    Cat,
    Dog,
    Dinosaur,
    Fox,
    Bear,
    Rabbit,
    Arab,
    Tofu,
    ;

    companion object {
        fun Int.toAppearance(): Appearance {
            return when (this) {
                0 -> Cat
                1 -> Dog
                2 -> Dinosaur
                3 -> Fox
                4 -> Bear
                5 -> Rabbit
                6 -> Arab
                7 -> Tofu
                else -> Empty
            }
        }

        fun Appearance.toModel(): String? {
            return when (this) {
                Empty -> null
                Cat -> "고양이상"
                Dog -> "강아지상"
                Dinosaur -> "공룡상"
                Fox -> "여우상"
                Bear -> "곰상"
                Rabbit -> "토끼상"
                Arab -> "아랍상"
                Tofu -> "두부상"
            }
        }
    }
}
