package alexandrefournier.topheadlines.ui.viewmodel

import alexandrefournier.topheadlines.ArticlesRepository
import alexandrefournier.topheadlines.Destination
import alexandrefournier.topheadlines.model.Article
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArticleDetailsViewModel(
    private val repository: ArticlesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val articleId: String = savedStateHandle.toRoute<Destination.ArticleDetails>().id

    private val _uiState = MutableStateFlow<ArticleDetailsUiState>(ArticleDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        val article = repository.findArticleById(articleId)
        if (article != null) {
            _uiState.value = ArticleDetailsUiState.Loaded(article)
        } else {
            _uiState.value = ArticleDetailsUiState.Error("Unknown error")
        }

    }
}


sealed interface ArticleDetailsUiState {
    data object Loading : ArticleDetailsUiState
    data class Loaded(val article: Article) : ArticleDetailsUiState
    data class Error(val message: String) : ArticleDetailsUiState
}
