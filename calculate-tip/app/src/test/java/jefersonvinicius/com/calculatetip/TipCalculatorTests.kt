package jefersonvinicius.com.calculatetip

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {
    @Test
    fun calculateTip_20PercentNoRoundUp() {
        val amount = 10.00
        val tipPercent = 20.00
        val actualTip = calculateTip(amount, tipPercent, false)
        assertEquals(2.0, actualTip)
    }

    @Test
    fun calculateTip_20PercentWithRoundUp() {
        val amount = 11.00
        val tipPercent = 20.00
        val actualTip = calculateTip(amount, tipPercent, true)
        assertEquals(3.0, actualTip)
    }
}