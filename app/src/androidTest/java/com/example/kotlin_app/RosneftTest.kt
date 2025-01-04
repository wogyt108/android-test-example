package com.example.kotlin_app

import android.util.Log
import androidx.compose.ui.test.hasText
import androidx.test.ext.junit.rules.activityScenarioRule
import com.kaspersky.components.kautomator.component.common.views.UiView
import com.kaspersky.components.kautomator.component.edit.UiEditText
import com.kaspersky.components.kautomator.component.text.UiButton
import com.kaspersky.components.kautomator.screen.UiScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
val rosneftPackage = "ru.pichesky.rosneft"

object RosneftScreen : UiScreen<RosneftScreen>() {
    override val packageName: String = rosneftPackage
    val achiveButton = UiButton{withText( "Достижения")}
}

class RosneftTest : TestCase() {


    @Test
    fun testPlus() = run {
        step("Open Rosneft") {
            with(device.targetContext) {
                val intent = packageManager.getLaunchIntentForPackage(rosneftPackage)
                startActivity(intent)
            }
        }

        step("проверка доступности"){
            RosneftScreen{
                achiveButton.isEnabled()
                achiveButton.click()
            }
        }


    }

}