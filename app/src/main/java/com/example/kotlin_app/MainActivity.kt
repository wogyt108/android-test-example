package com.example.kotlin_app

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlin_app.ui.theme.KotlinappTheme
import java.io.Serializable
import android.os.Parcelable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.parcelize.Parcelize

@Parcelize
data class CalculatorState(
    var n1s: String = "",
    var n2s: String = "",
    var rs: String = "",
) : Parcelable, Serializable {
    fun withN1S(s: String): CalculatorState {
        return this.copy(n1s = s)
    }

    fun withN2S(s: String): CalculatorState {
        return this.copy(n2s = s)
    }

    fun sum(): CalculatorState {
        return this.copy(rs = (n1s.toDouble() + n2s.toDouble()).toString())
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
    var calc by rememberSaveable { mutableStateOf(CalculatorState()) }
    Column(
        modifier = modifier
    ) {
        Text("Первое число:")
        NumberField(calc.n1s) { calc = calc.withN1S(it) }
        Text("Второе число:")
        NumberField(calc.n2s) { calc = calc.withN2S(it) }
        Button(onClick = { calc = calc.sum() }) {
            Text("+")
        }
        Text("Результат:")
        Text(calc.rs)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinappTheme {
        Text("Наш калькулятор")
    }
}


@Composable
fun NumberField(value: String, onValueChange: (String) -> Unit) {
    val doublePattern = Regex("""^\d+(\.\d*)?$""")
    val numberKeyboard = KeyboardOptions(
        keyboardType = KeyboardType.Number
    )
    TextField(value, onValueChange = { newValue ->
        if (newValue.isEmpty() || newValue.matches(doublePattern)) {
            onValueChange(newValue)
        }
    }, keyboardOptions = numberKeyboard, singleLine = true)
}