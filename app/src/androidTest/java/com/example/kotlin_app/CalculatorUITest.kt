package com.example.kotlin_app

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

class CalculatorUITest {
    @Rule
    @JvmField
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun initialState() {
        onComposeScreen<CalculatorScreen>(composeTestRule) {
            sum {
                assertTextContains("+")
                assertIsNotEnabled()
            }
            sub {
                assertTextContains("-")
                assertIsNotEnabled()
            }
            mul {
                assertTextContains("*")
                assertIsNotEnabled()
            }
            div {
                assertTextContains("/")
                assertIsNotEnabled()
            }
            n1 {
                assertTextEquals("")
            }
            n2 {
                assertTextEquals("")
            }
        }
    }

    @Test
    fun enableButtonsIfHasNumbers() {
        onComposeScreen<CalculatorScreen>(composeTestRule){
            n1.performTextInput("1")
            n2.performTextInput("2")
            sum {
                assertIsEnabled()
            }
            sub {
                assertIsEnabled()
            }
            mul {
                assertIsEnabled()
            }
            div {
                assertIsEnabled()
            }
        }
    }

    @Test
    fun onlyNumbersInEdit() {
        onComposeScreen<CalculatorScreen>(composeTestRule){
            n1.performTextInput("1")
            n1.performTextInput("b")
            n1.performTextInput("2")
            n1.performTextInput("c")
            n1.performTextInput("3")
            n1.assertTextEquals("123")
        }
    }
    @Test
    fun testSum() {
        onComposeScreen<CalculatorScreen>(composeTestRule){
            result.assertTextEquals("")
            val v1 = Random.nextDouble()
            val v2 = Random.nextDouble()
            n1.performTextInput(v1.toString())
            n2.performTextInput(v2.toString())
            sum.performClick()
            result.assertTextEquals((v1+v2).toString())
        }
    }
    @Test
    fun testSub() {
        onComposeScreen<CalculatorScreen>(composeTestRule){
            result.assertTextEquals("")
            val v1 = Random.nextDouble()
            val v2 = Random.nextDouble()
            n1.performTextInput(v1.toString())
            n2.performTextInput(v2.toString())
            sub.performClick()
            result.assertTextEquals((v1-v2).toString())
        }
    }
    @Test
    fun testMul() {
        onComposeScreen<CalculatorScreen>(composeTestRule){
            result.assertTextEquals("")
            val v1 = Random.nextDouble()
            val v2 = Random.nextDouble()
            n1.performTextInput(v1.toString())
            n2.performTextInput(v2.toString())
            mul.performClick()
            result.assertTextEquals((v1*v2).toString())
        }
    }
    @Test
    fun testDiv() {
        onComposeScreen<CalculatorScreen>(composeTestRule){
            result.assertTextEquals("")
            val v1 = Random.nextDouble()
            val v2 = Random.nextDouble()
            n1.performTextInput(v1.toString())
            n2.performTextInput(v2.toString())
            div.performClick()
            result.assertTextEquals((v1/v2).toString())
        }
    }

}