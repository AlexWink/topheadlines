package alexandrefournier.topheadlines

import alexandrefournier.topheadlines.test.TestArticlesRepository
import alexandrefournier.topheadlines.ui.screen.ArticleDetailsScreen
import alexandrefournier.topheadlines.ui.viewmodel.ArticleDetailsUiState
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ArticleDetailsScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun progressIndicatorExistsWhenStateIsLoading() {
        composeTestRule.setContent {
            ArticleDetailsScreen(
                uiState = ArticleDetailsUiState.Loading,
                onBackClicked = { },
            )
        }
        composeTestRule
            .onNodeWithTag("articleDetailsProgressIndicator").assertExists()
    }

    @Test
    fun backButtonExistsWhenStateIsError() {
        composeTestRule.setContent {
            ArticleDetailsScreen(
                uiState = ArticleDetailsUiState.Error(""),
                onBackClicked = { },
            )
        }
        composeTestRule
            .onNodeWithTag("articleDetailsBackButton").assertExists()
    }

    @Test
    fun errorMessageExistsWhenStateIsError() {
        composeTestRule.setContent {
            ArticleDetailsScreen(
                uiState = ArticleDetailsUiState.Error("testMessage"),
                onBackClicked = { },
            )
        }
        composeTestRule
            .onNodeWithText("testMessage").assertExists()
    }

    @Test
    fun articleExistsWhenStateIsLoaded() {
        composeTestRule.setContent {
            ArticleDetailsScreen(
                uiState = ArticleDetailsUiState.Loaded(TestArticlesRepository.sampleArticlesResources[0]),
                onBackClicked = { },
            )
        }
        composeTestRule
            .onNodeWithText("testTitle").assertExists()
    }
}