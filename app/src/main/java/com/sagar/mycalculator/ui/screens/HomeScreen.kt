package com.sagar.mycalculator.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sagar.mycalculator.business.CalcUiState
import com.sagar.mycalculator.use_cases.CalculatorAction


@Composable
fun CalculatorScreen(
    onClick: (CalculatorAction) -> Unit,
    uiState: CalcUiState
){
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(240.dp))
        Column(
            modifier = Modifier.wrapContentSize()
        ) {
            Text(
                text = "${uiState.num1} ${uiState.operand?.symbol ?: ""} ${uiState.num2}",
                fontSize = 48.sp,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp),
                maxLines = 2,
                color = Color.Yellow,
                textAlign = TextAlign.Right
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(3f)
                ) {
                    FirstRow(onClick = onClick,
                        modifier = Modifier.weight(1f)
                        )
                    NumberColumn(onClick = onClick,
                        modifier = Modifier.weight(3f)
                    )
                    BottomRow(onClick = onClick,
                        modifier = Modifier.weight(1f)
                        )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Operands(onCLick = onClick,
                    modifier = Modifier.weight(1f)
                    )
            }
        }
    }
}