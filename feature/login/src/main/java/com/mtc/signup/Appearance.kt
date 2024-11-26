package com.mtc.signup

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
                Cat -> "cat"
                Dog -> "dog"
                Dinosaur -> "dinosaur"
                Fox -> "fox"
                Bear -> "bear"
                Rabbit -> "rabbit"
                Arab -> "arab"
                Tofu -> "tofu"
            }
        }
    }
}
