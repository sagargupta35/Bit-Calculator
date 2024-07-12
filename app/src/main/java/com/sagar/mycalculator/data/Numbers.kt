package com.sagar.mycalculator.data

import com.sagar.mycalculator.use_cases.CalculatorAction
import com.sagar.mycalculator.use_cases.Operand

val numbersMap = mapOf<String, CalculatorAction>(
    "1" to CalculatorAction.Number(1),
    "2" to CalculatorAction.Number(2),
    "3" to CalculatorAction.Number(3),
    "4" to CalculatorAction.Number(4),
    "5" to CalculatorAction.Number(5),
    "6" to CalculatorAction.Number(6),
    "7" to CalculatorAction.Number(7),
    "8" to CalculatorAction.Number(8),
    "9" to CalculatorAction.Number(9),
    "0" to CalculatorAction.Number(0)
)

val operands = listOf<Pair<String, CalculatorAction>>(
    "^" to CalculatorAction.Operation(Operand.Xor),
    "|" to CalculatorAction.Operation(Operand.Or),
    "bn" to CalculatorAction.Operation(Operand.BaseN),
    "&" to CalculatorAction.Operation(Operand.And),
)