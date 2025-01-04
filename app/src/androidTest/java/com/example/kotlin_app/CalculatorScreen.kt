package com.example.kotlin_app

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class CalculatorScreen(semanticsProvider: SemanticsNodeInteractionsProvider) :
    ComposeScreen<CalculatorScreen>(
        semanticsProvider = semanticsProvider
    ) {
    val n1: KNode = child {
        hasContentDescription("n1")
    }
    val n2: KNode = child {
        hasContentDescription("n2")
    }
    val sum: KNode = child {
        hasContentDescription("sum")
    }
    val sub: KNode = child {
        hasContentDescription("sub")
    }
    val mul: KNode = child {
        hasContentDescription("mul")
    }
    val div: KNode = child {
        hasContentDescription("div")
    }
    val result: KNode = child {
        hasContentDescription("result")
    }
}