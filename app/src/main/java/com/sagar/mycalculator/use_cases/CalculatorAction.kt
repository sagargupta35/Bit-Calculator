package com.sagar.mycalculator.use_cases

sealed class CalculatorAction {
    data class Number(val number: Int): CalculatorAction()
    data object Calculate: CalculatorAction()
    data object Clear: CalculatorAction()
    data class Operation(val operand: Operand): CalculatorAction()
    data object Delete: CalculatorAction()
}