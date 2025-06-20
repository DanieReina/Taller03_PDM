import org.junit.Assert.assertEquals
import org.junit.Test
import com.example.testeableapp.ui.Screens.calculateTip

class TipCalculationTest {

    @Test
    fun calculateTip_with37PercentAndRoundUp_returnsCorrectTip() {

        val amount = 100.0
        val tipPercent = 37
        val roundUp = true

        val result = calculateTip(amount, tipPercent, roundUp)

        assertEquals(37.0, result, 0.01)
    }
}


class NegativeCalculationTest {

    @Test
    fun calculateTip_withNegativeAmount_returnsZero() {

        val amount = -50.0
        val tipPercent = 20
        val roundUp = false

        val result = calculateTip(amount, tipPercent, roundUp)

        assertEquals(0.0, result, 0.01)
    }
}


class TotalPayCalculationTest {

    @Test
    fun totalPerPerson_withValidInputs_returnsCorrectAmount() {

        val amount = 120.0
        val tipPercent = 20
        val roundUp = false
        val people = 4

        val tip = calculateTip(amount, tipPercent, roundUp)
        val total = amount + tip
        val totalPerPerson = if (people > 0) total / people else 0.0

        assertEquals(36.0, totalPerPerson, 0.01)
    }
}

class calculateTip_withZeroPercent_returnsZero {
    @Test
    fun calculateTip_withZeroPercent_returnsZero() {
        val amount = 250.0
        val tipPercent = 0
        val roundUp = false

        val result = calculateTip(amount, tipPercent, roundUp)

        assertEquals(0.0, result, 0.01)
    }

}

class calculateTip_roundUpFromSmallDecimal_returnsCeil{
    @Test
    fun calculateTip_roundUpFromSmallDecimal_returnsCeil() {
        val amount = 80.05 // 15% => 12.0075
        val tipPercent = 15
        val roundUp = true

        val result = calculateTip(amount, tipPercent, roundUp)

        assertEquals(13.0, result, 0.01)
    }

}


