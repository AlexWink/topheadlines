package alexandrefournier.topheadlines

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun onArticleClickedDetailsScreenExists() {
        composeTestRule.waitUntil(
            timeoutMillis = 500,
            condition = { composeTestRule.onNodeWithTag("articlesItem0").isDisplayed() })
        composeTestRule.onRoot().printToLog("composeTestRule.onRoot")
        composeTestRule.onNodeWithTag("articlesItem0").performClick()
        composeTestRule.onNodeWithText("testDescription").assertExists()
    }
}