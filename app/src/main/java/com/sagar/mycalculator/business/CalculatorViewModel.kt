package com.sagar.mycalculator.business

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sagar.mycalculator.use_cases.CalculatorAction
import com.sagar.mycalculator.use_cases.Operand
import java.lang.StringBuilder
import kotlin.math.ceil
import kotlin.math.ln
import kotlin.math.pow

data class CalcUiState(
    val num1: String = "",
    val operand: Operand? = null,
    val num2: String = "",
    val clear: Boolean = false
)


class CalculatorViewModel: ViewModel() {
    var uiState by mutableStateOf(CalcUiState())
    private set

    fun CalcEvent(event: CalculatorAction){
        when(event){
            is CalculatorAction.Number -> {
                updateNumber(event.number)
            }
            is CalculatorAction.Delete -> delete()
            is CalculatorAction.Clear -> clear()
            is CalculatorAction.Operation -> operate(event.operand)
            is CalculatorAction.Calculate -> calculate()
        }
    }

    private fun updateNumber(n: Int){
        if(uiState.clear) clear()
        uiState = if(uiState.operand == null){
            uiState.copy(
                num1 = uiState.num1+n
            )
        } else{
            uiState.copy(
                num2 = uiState.num2+n
            )
        }
        Log.d("TAG", "Number is updated into uiState as ${uiState.num1}")
    }

    private fun calculate() {
        if(uiState.num2.isBlank()) return
        when(uiState.operand) {
            is Operand.Xor -> xor()
            is Operand.And -> and()
            is Operand.Or -> or()
            is Operand.BaseN -> baseN(uiState.num2.toInt())
            else -> return
        }
    }

    private fun or(){
        val num1 = uiState.num1.toInt()
        val num2 = uiState.num2.toInt()
        uiState = uiState.copy(
            num1 = num1.or(num2).toString(),
            num2 = "",
            clear = false,
            operand = null
        )
    }


    private fun and(){
        val num1 = uiState.num1.toInt()
        val num2 = uiState.num2.toInt()
        uiState = uiState.copy(
            num1 = num1.and(num2).toString(),
            num2 = "",
            clear = false,
            operand = null
        )
    }

    private fun xor(){
        val num1 = uiState.num1.toInt()
        val num2 = uiState.num2.toInt()
        uiState = uiState.copy(
            num1 = num1.xor(num2).toString(),
            num2 = "",
            clear = false,
            operand = null
        )
    }

    private fun clear(){
        uiState = CalcUiState()
    }

    private fun delete(){
        if(uiState.clear){
            clear()
            return
        }
        if(uiState.num1.isBlank()) return
        uiState = if(uiState.operand == null){
            uiState.copy(num1 = uiState.num1.dropLast(1))
        } else{
            if(uiState.num2.isBlank()){
                uiState.copy(operand = null)
            } else{
                uiState.copy(num2 = uiState.num2.dropLast(1))
            }
        }
    }


    //change
    private fun operate(operation: Operand){
        if(uiState.clear) clear()
        if(uiState.num1.isBlank()) return
        if(uiState.num2.isNotBlank()){
            when(operation){
                is Operand.Xor, Operand.Or, Operand.And, Operand.BaseN -> uiState = uiState.copy(operand = operation)
                else -> return
            }
        }
        when(operation){
            is Operand.Xor, Operand.Or, Operand.And, Operand.BaseN -> uiState = uiState.copy(operand = operation)
            is Operand.Compliment -> {
                if(uiState.clear){
                    clear()
                    return
                }
                var num1 = uiState.num1.toIntOrNull() ?: return
                num1 = if(num1.and(num1.minus(1)) == 0) num1.minus(1)
                    else num1.xor((2.0.pow(ceil(ln(num1.toDouble()) / ln(2.0))) -1).toInt())
                uiState = uiState.copy(num1 = num1.toString(),
                    operand = null,
                    num2 = "",
                    clear = false
                    )
            }
            is Operand.Binary -> baseN(2)
        }
    }

    private fun baseN(base: Int){
        var num1 = uiState.num1.toIntOrNull() ?: return
        val binaryNum = StringBuilder()
        while (num1 > 0){
            binaryNum.insert(0, num1.mod(base))
            num1 = num1.div(base)
        }
        uiState = uiState.copy(
            num1 = binaryNum.toString(),
            operand = null,
            num2 = "",
            clear = true
        )
    }
}