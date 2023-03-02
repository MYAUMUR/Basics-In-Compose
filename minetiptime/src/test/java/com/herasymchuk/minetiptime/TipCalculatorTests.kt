package com.herasymchuk.minetiptime

import org.junit.Assert
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun calculate_20_percent_tip_no_roundup() {
        val amount: Double = 10.0
        val tipPercent: Double = 20.0
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount, tipPercent, false)
        Assert.assertEquals(expectedTip, actualTip)
    }
}