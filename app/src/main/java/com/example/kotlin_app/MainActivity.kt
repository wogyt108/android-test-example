package com.example.kotlin_app

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlin_app.ui.theme.KotlinappTheme
import java.io.Serializable
import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.parcelize.Parcelize

@Parcelize
data class CalculatorState (
    var n1s: String = "",
    var n2s: String = "",
    var rs: String = "",
):Parcelable, Serializable {
    val n1: Double get() =
        when {
            n1s.isEmpty() -> 0.0
            n1s.last() == '.' -> (n1s + "0").toDouble()
            else -> n1s.toDouble()
        }
    val n2: Double get() =
        when {
            n2s.isEmpty() -> 0.0
            n2s.last() == '.' -> (n2s + "0").toDouble()
            else -> n2s.toDouble()
        }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinappTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Calculator(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



@Composable
fun Calculator(
    modifier: Modifier = Modifier
) {
    var calculator by rememberSaveable { mutableStateOf(CalculatorState()) }
    val doublePattern = remember { Regex("""^\d+(\.\d*)?$""") }
    Column(
        modifier = modifier
    ) {
        Text("Первое число:")
        val numberKeyboard = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
        TextField(calculator.n1s, onValueChange = { newValue ->
            if (newValue.isEmpty() || newValue.matches(doublePattern)) {
                calculator = calculator.copy(n1s = newValue)
            }
        }, keyboardOptions = numberKeyboard, singleLine = true)
        Text("Второе число:")
        TextField(calculator.n2s, onValueChange = { newValue ->
            if (newValue.isEmpty() || newValue.matches(doublePattern)) {
                calculator = calculator.copy(n2s = newValue)
            }
        }, keyboardOptions = numberKeyboard, singleLine = true)

        Button(
            onClick = {
                Log.d("DERP", "check out my stack trace")
                val c = calculator
                calculator = calculator.copy(rs = (c.n1s.toDouble() + c.n2s.toDouble()).toString())
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
        Text(calculator.rs)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinappTheme {
        Text("Наш калькулятор")
    }
}

