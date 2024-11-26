package alexandrefournier.topheadlines

import alexandrefournier.topheadlines.test.MainDispatcherRule
import alexandrefournier.topheadlines.test.TestArticlesRepository
import alexandrefournier.topheadlines.ui.viewmodel.ArticlesUiState
import alexandrefournier.topheadlines.ui.viewmodel.ArticlesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArticlesViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val articlesRepository = TestArticlesRepository()

    private lateinit var viewModel: ArticlesViewModel

    @Before
    fun setup() {
        viewModel = ArticlesViewModel(articlesRepository)
    }

    @Test
    fun stateIsInitiallyLoading() = runTest {
        assertEquals(ArticlesUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun stateIsSuccessAfterLoad() = runTest {
        delay(TestArticlesRepository.FETCH_DELAY)
        assertEquals(
            ArticlesUiState.Loaded(
                TestArticlesRepository.sampleArticlesResources
            ), viewModel.uiState.value
        )
    }

    @Test
    fun stateIsErrorAfterFailedLoad() = runTest {
        articlesRepository.fetchResult = Result.failure(Exception("TestError"))
        viewModel.fetchArticles()
        delay(TestArticlesRepository.FETCH_DELAY)
        assertEquals(
            ArticlesUiState.Error("TestError"), viewModel.uiState.value
        )
    }
}