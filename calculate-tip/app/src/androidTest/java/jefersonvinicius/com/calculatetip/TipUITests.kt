package jefersonvinicius.com.calculatetip

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import jefersonvinicius.com.calculatetip.ui.theme.CalculateTipTheme
import org.junit.Rule
import org.junit.Test

class TipUITests {
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculateTip_20PercentNoRoundUp() {
        composeTestRule.setContent {
            CalculateTipTheme {
                CalculateTipApp()
            }
        }
        composeTestRule.onNodeWithText("Bill amount").performTextInput("10")
        composeTestRule.onNodeWithText("Tip (%)").performTextInput("20")
        composeTestRule.onNodeWithText("Tip Amount: $2.00").assertExists()
    }
}