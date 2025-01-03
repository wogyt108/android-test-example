package com.example.kotlin_app

import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlin_app.ui.theme.KotlinappTheme

data class CalculatorState (
    var firstNumber: String = "",
    var secondNumber: String = "",
    var result: String = "",
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val calculator = remember { mutableStateOf(CalculatorState()) }
            KotlinappTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Calculator(
                        calculator,
                        activity = this,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



@Composable
fun Calculator(
    calculatorState: MutableState<CalculatorState>,
    activity: MainActivity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text("Первое число:")
        TextField(calculatorState.value.firstNumber, onValueChange = { newValue ->
            calculatorState.value = calculatorState.value.copy(firstNumber = newValue)
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Text("Второе число:")
        TextField(calculatorState.value.secondNumber, onValueChange = { newValue ->
            calculatorState.value = calculatorState.value.copy(secondNumber = newValue)
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Button(
            onClick = {
                val c = calculatorState.value
                calculatorState.value = calculatorState.value.copy(result = (c.firstNumber.toDouble() + c.secondNumber.toDouble()).toString())
//                AlertDialog.Builder(activity)
//                    .setMessage("Скоро будет считать ${calculatorState.value.firstNumber} + ${calculatorState.value.secondNumber}")
//                    .setTitle("Проблема")
//                    .setCancelable(true)
//                    .create()
//                    .show()
            }) {
            Text("+")
        }
        Text("Результат:")
        Text(calculatorState.value.result)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinappTheme {
        Text("Наш калькулятор")
    }
}