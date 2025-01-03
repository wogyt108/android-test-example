package com.example.kotlin_app

import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test


object CalcScreen : UiScreen<CalcScreen>() {

    override val packageName: String = "com.example.kotlin_app"

    val n1edit = UiEditText { withContentDescription("n1") }
    val n2edit = UiEditText { withContentDescription("n2") }
    val sumButton = UiButton { withContentDescription("sum") }
    val subButton = UiButton { withContentDescription("sub") }
    val mulButton = UiButton { withContentDescription("mul") }
    val divButton = UiButton { withContentDescription("div") }
}

class ExampleKaumatorTest : TestCase() {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

//    @Test
//    fun testPlus() = run {
//        step("Open Calculator") {
//            with(device.targetContext) {
//                val intent = packageManager.getLaunchIntentForPackage(CALC_PACKAGE)
//                startActivity(intent)
//            }
//        }

    //       step("Начальное состояние") {
    //          CalcScreen {
    //             n1edit.isDisplayed()
    //              n2edit.isDisplayed()
    //               plusButton.isDisplayed()
//            }
//        }
//    }

    @Test
    fun testInitialVisualComponents() = run {
        step("проверяем, что есть поля ввода и они пустые") {
            CalcScreen {
                n1edit.isDisplayed()
                n1edit.hasEmptyText()

                n2edit.isDisplayed()
                n2edit.hasEmptyText()
            }
        }
        step("проверяем, что видны все кнопки") {
            CalcScreen {
                sumButton.isDisplayed()
                subButton.isDisplayed()
                mulButton.isDisplayed()
                divButton.isDisplayed()
            }
        }
    }

    @Test
    fun buttonsDisabledOnEmptyAndShownOnFilled() = run {
        step("сбросим числа") {
            CalcScreen.n1edit.clearText()
            CalcScreen.n2edit.clearText()
        }
        step("кнопки должны быть отключены") {
            CalcScreen {
                sumButton.isDisabled()
                subButton.isDisabled()
                mulButton.isDisabled()
                divButton.isDisabled()
            }
        }
        step("установим числа") {
            CalcScreen.n1edit.typeText("12")
            CalcScreen.n2edit.typeText("15")
        }
        step("кнопки должны быть включены") {
            CalcScreen {
                sumButton.isEnabled()
                subButton.isEnabled()
                mulButton.isEnabled()
                divButton.isEnabled()
            }
        }
    }


    companion object {

        private const val CALC_PACKAGE = "com.example.kotlin_app"
    }
}