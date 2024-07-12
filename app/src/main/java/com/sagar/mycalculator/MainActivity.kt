package com.sagar.mycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sagar.mycalculator.business.CalculatorViewModel
import com.sagar.mycalculator.ui.screens.CalculatorScreen
import com.sagar.mycalculator.ui.theme.MyCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = viewModel<CalculatorViewModel>()
                    val state = viewModel.uiState
                    CalculatorScreen(
                        onClick = viewModel::CalcEvent,
                        uiState = state
                    )
                }
            }
        }
    }
}
