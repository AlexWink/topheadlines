package alexandrefournier.topheadlines

import alexandrefournier.topheadlines.test.TestArticlesRepository
import alexandrefournier.topheadlines.ui.screen.ArticlesScreen
import alexandrefournier.topheadlines.ui.viewmodel.ArticlesUiState
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class ArticlesScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun progressIndicatorExistsWhenStateIsLoading() {
        composeTestRule.setContent {
            ArticlesScreen(
                uiState = ArticlesUiState.Loading,
                onRefreshClicked = { },
                onArticleClicked = { }
            )
        }
        composeTestRule
            .onNodeWithTag("articlesProgressIndicator").assertExists()
    }

    @Test
    fun refreshButtonExistsWhenStateIsError() {
        composeTestRule.setContent {
            ArticlesScreen(
                uiState = ArticlesUiState.Error(""),
                onRefreshClicked = { },
                onArticleClicked = { }
            )
        }
        composeTestRule
            .onNodeWithTag("articlesRefreshButton").assertExists()
    }

    @Test
    fun errorMessageExistsWhenStateIsError() {
        composeTestRule.setContent {
            ArticlesScreen(
                uiState = ArticlesUiState.Error("testMessage"),
                onRefreshClicked = { },
                onArticleClicked = { }
            )
        }
        composeTestRule
            .onNodeWithText("testMessage", substring = true).assertExists()
    }

    @Test
    fun articleExistsWhenStateIsLoaded() {
        composeTestRule.setContent {
            ArticlesScreen(
                uiState = ArticlesUiState.Loaded(TestArticlesRepository.sampleArticlesResources),
                onRefreshClicked = { },
                onArticleClicked = { }
            )
        }
        composeTestRule
            .onNodeWithText("testTitle").assertExists()
    }
}