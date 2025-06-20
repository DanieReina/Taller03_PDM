import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.testeableapp.MainActivity
import org.junit.Rule
import org.junit.Test

class TipCalculatorUiTest1 {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun tipChangesWhenRoundingCheckboxIsChecked() {
        composeTestRule.onNodeWithTag("bill_input").performTextInput("101")

        val unroundedTip = composeTestRule
            .onNodeWithTag("tip_display")
            .assertExists()
            .assertIsDisplayed()
            .fetchSemanticsNode()
            .config.getOrNull(SemanticsProperties.Text)?.firstOrNull()?.text

        composeTestRule.onNodeWithTag("round_checkbox").performClick()

        val roundedTip = composeTestRule
            .onNodeWithTag("tip_display")
            .assertExists()
            .assertIsDisplayed()
            .fetchSemanticsNode()
            .config.getOrNull(SemanticsProperties.Text)?.firstOrNull()?.text

        assert(unroundedTip != null && roundedTip != null && unroundedTip != roundedTip)
    }
}


class TipCalculatorUiTest2 {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun tipChangesWhenSliderIsMoved() {
        composeTestRule.onNodeWithTag("bill_input").performTextInput("200")

        val initialTip = composeTestRule
            .onNodeWithTag("tip_display")
            .assertExists()
            .fetchSemanticsNode()
            .config.getOrNull(SemanticsProperties.Text)?.firstOrNull()?.text

        composeTestRule
            .onNodeWithTag("tip_slider")
            .performSemanticsAction(SemanticsActions.SetProgress) {
                it(0.25f)
            }

        val updatedTip = composeTestRule
            .onNodeWithTag("tip_display")
            .assertExists()
            .fetchSemanticsNode()
            .config.getOrNull(SemanticsProperties.Text)?.firstOrNull()?.text

        assert(initialTip != null && updatedTip != null && initialTip != updatedTip)
    }

}

class TipCalculatorUiTest3 {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    @Test
    fun uiElementsAreVisible() {
        composeTestRule
            .onNodeWithTag("bill_input")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("tip_slider")
            .assertExists()
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("people_input")
            .assertExists()
            .assertIsDisplayed()
    }

}

class invalidPeopleInput_defaultsToOnePerson{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun invalidPeopleInput_defaultsToOnePerson() {

        composeTestRule.onNodeWithTag("bill_input").performTextInput("100")

        composeTestRule.onNodeWithTag("people_input").performTextInput("abc")

        val totalPerPerson = composeTestRule
            .onNodeWithTag("total_display")
            .assertExists()
            .fetchSemanticsNode()
            .config.getOrNull(SemanticsProperties.Text)?.firstOrNull()?.text

        assert(totalPerPerson?.contains("115.0") == true)
    }


}

class totalPerPersonUpdatesWhenPeopleChange{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun totalPerPersonUpdatesWhenPeopleChange() {
        composeTestRule.onNodeWithTag("bill_input").performTextInput("120")

        var total = composeTestRule
            .onNodeWithTag("total_display")
            .fetchSemanticsNode()
            .config.getOrNull(SemanticsProperties.Text)?.firstOrNull()?.text

        composeTestRule.onNodeWithTag("people_input").performTextClearance()
        composeTestRule.onNodeWithTag("people_input").performTextInput("4")

        val updatedTotal = composeTestRule
            .onNodeWithTag("total_display")
            .fetchSemanticsNode()
            .config.getOrNull(SemanticsProperties.Text)?.firstOrNull()?.text

        assert(total != null && updatedTotal != null && total != updatedTotal)
    }

}


