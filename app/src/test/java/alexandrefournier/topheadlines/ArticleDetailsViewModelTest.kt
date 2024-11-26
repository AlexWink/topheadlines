package alexandrefournier.topheadlines

import alexandrefournier.topheadlines.test.MainDispatcherRule
import alexandrefournier.topheadlines.test.TestArticlesRepository
import alexandrefournier.topheadlines.ui.viewmodel.ArticleDetailsUiState
import alexandrefournier.topheadlines.ui.viewmodel.ArticleDetailsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArticleDetailsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val articlesRepository = TestArticlesRepository()

    private lateinit var viewModel: ArticleDetailsViewModel

    @Before
    fun setup() {
        viewModel = ArticleDetailsViewModel(articlesRepository)
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        assertEquals(ArticleDetailsUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun stateIsSuccessAfterLoad() = runTest {
        viewModel.setArticleId("testId")
        assertEquals(
            ArticleDetailsUiState.Loaded(
                TestArticlesRepository.sampleArticlesResources[0]
            ), viewModel.uiState.value
        )
    }

    @Test
    fun stateIsErrorAfterFailedLoad() = runTest {
        viewModel.setArticleId("fail")
        assertEquals(
            ArticleDetailsUiState.Error("Unknown error"), viewModel.uiState.value
        )
    }
}