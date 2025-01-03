package com.example.kotlin_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlin_app.ui.theme.KotlinappTheme

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
        modifier = modifier.padding(horizontal = 10.dp)
    ) {
        Text("Первое число:")
        NumberField("n1", calc.n1s) { calc = calc.withN1S(it) }
        Text("Второе число:")
        NumberField("n2",calc.n2s) { calc = calc.withN2S(it) }

        Row(horizontalArrangement =Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
            Button(onClick = { calc = calc.sum() }, modifier = Modifier.semantics {
                contentDescription = "sum"
            }) {
                Text("+")
            }
            Button(onClick = { calc = calc.sub() }, modifier = Modifier.semantics {
                contentDescription = "sub"
            }) {
                Text("-")
            }
            Button(onClick = { calc = calc.mul() }, modifier = Modifier.semantics {
                contentDescription = "mul"
            }) {
                Text("*")
            }
            Button(onClick = { calc = calc.div() }, modifier = Modifier.semantics {
                contentDescription = "div"
            }) {
                Text("/")
            }
        }
        Text("Результат:")
        ClickableText(
            AnnotatedString(calc.rs),
            onClick = {calc = calc.copy(n1s = calc.rs, rs = "") },
            style = TextStyle(color = LocalContentColor.current)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinappTheme {
        Text("Наш калькулятор")
    }
}

var i = 0
@Composable
fun NumberField(id:String, value: String,  onValueChange: (String) -> Unit) {
    val doublePattern = Regex("""^\d+(\.\d*)?$""")
    val numberKeyboard = KeyboardOptions(
        keyboardType = KeyboardType.Number
    )
    TextField(value, onValueChange = { newValue ->
        if (newValue.isEmpty() || newValue.matches(doublePattern)) {
            onValueChange(newValue)
        }
    }, keyboardOptions = numberKeyboard, singleLine = true,
        modifier =
            Modifier.fillMaxWidth().semantics {
                contentDescription = id
            })
}