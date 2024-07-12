package com.sagar.mycalculator.use_cases

sealed class Operand(val symbol: String) {
    data object Xor: Operand("^")
    data object Or: Operand("|")
    data object Compliment: Operand("~")
    data object BaseN: Operand("bn")
    data object Binary: Operand("01")
    data object And: Operand("&")
}