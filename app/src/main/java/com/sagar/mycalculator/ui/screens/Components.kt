package com.sagar.mycalculator.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sagar.mycalculator.data.*
import com.sagar.mycalculator.use_cases.CalculatorAction
import com.sagar.mycalculator.use_cases.Operand

private val tag = "TAG"

@Composable
fun CalButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    symbol: String,
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(CircleShape)
            .size(80.dp)
            .clickable {
                Log.d(tag, "Button $symbol is being clicked")
                onClick()
            }
            .then(modifier)
    ) {
        Text(text = symbol,
            fontSize = 32.sp,
            color = Color.White,
            )
    }
}

@Composable
fun NumberRow(
    actions: List<Pair<String, CalculatorAction>>,
    modifier: Modifier = Modifier,
    onClick: (CalculatorAction) -> Unit
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for((sym, act) in actions){
            CalButton(onClick = { onClick(act) },
                symbol = sym,
                modifier = Modifier
                    .background(Color.DarkGray)
                    .aspectRatio(1f)
                    .weight(1f)
            )
        }
    }
}



@Composable
fun NumberColumn(
    modifier: Modifier = Modifier,
    actions: Map<String, CalculatorAction> = numbersMap,
    onClick: (CalculatorAction) -> Unit
){
    val firstRow = mutableListOf<Pair<String, CalculatorAction>>()
    val secondRow = mutableListOf<Pair<String, CalculatorAction>>()
    val thirdRow = mutableListOf<Pair<String, CalculatorAction>>()
    for(i in 1..3){
        firstRow += "$i" to actions["$i"]!!
    }
    for(i in 4..6){
        secondRow += "$i" to actions["$i"]!!
    }
    for(i in 7..9){
        thirdRow += "$i" to actions["$i"]!!
    }
    Column(
        modifier = Modifier
            .then(modifier),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NumberRow(actions = thirdRow,
            onClick = onClick
            )
        NumberRow(actions = secondRow,
            onClick = onClick
        )
        NumberRow(actions = firstRow,
            onClick = onClick
        )
    }
}


@Composable
fun Operands(
    modifier:Modifier = Modifier,
    operands: List<Pair<String, CalculatorAction>> = com.sagar.mycalculator.data.operands,
    onCLick: (CalculatorAction) -> Unit
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for(op in operands) {
            CalButton(
                onClick = { onCLick(op.second) },
                symbol = op.first,
                modifier = Modifier
                    .background(Color.DarkGray)
                    .aspectRatio(1f)
                    .weight(1f)
            )
        }
        CalButton(onClick = { onCLick(CalculatorAction.Calculate) },
            symbol = "=",
            modifier = Modifier
                .background(Color.DarkGray)
                .aspectRatio(1f)
                .weight(1f)
            )
    }
}


@Composable
fun FirstRow(
    modifier: Modifier = Modifier,
    onClick: (CalculatorAction) -> Unit
){
    Row(
        modifier =modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CalButton(onClick = { onClick(CalculatorAction.Clear) },
            symbol = "AC",
            modifier = Modifier
                .background(Color.DarkGray)
                .aspectRatio(1f)
                .weight(1f)
        )
        CalButton(onClick = { onClick(CalculatorAction.Operation(Operand.Binary)) },
            symbol = "01",
            modifier = Modifier
                .background(Color.DarkGray)
                .aspectRatio(1f)
                .weight(1f)
        )
        CalButton(onClick = { onClick(CalculatorAction.Delete) },
            symbol = "del",
            modifier = Modifier
                .background(Color.DarkGray)
                .aspectRatio(1f)
                .weight(1f)
        )
    }
}


@Composable
fun BottomRow(
    modifier: Modifier = Modifier,
    onClick: (CalculatorAction) -> Unit
){
    Row(
        modifier =modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CalButton(onClick = { onClick(CalculatorAction.Number(0)) },
            symbol = "0",
            modifier = Modifier
                .background(Color.DarkGray)
                .aspectRatio(1f)
                .weight(1f)
        )
        CalButton(onClick = { onClick(CalculatorAction.Operation(Operand.Compliment)) },
            symbol = "~",
            modifier = Modifier
                .background(Color.DarkGray)
                .aspectRatio(1f)
                .weight(1f)
        )
        CalButton(onClick = {  },
            symbol = ".",
            modifier = Modifier
                .background(Color.DarkGray)
                .aspectRatio(1f)
                .weight(1f)
        )
    }
}

