package alexandrefournier.topheadlines.ui.viewmodel

import alexandrefournier.topheadlines.ArticlesRepository
import alexandrefournier.topheadlines.model.Article
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ArticleDetailsViewModel(
    private val repository: ArticlesRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<ArticleDetailsUiState>(ArticleDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun setArticleId(id: String) {
        val article = repository.findArticleById(id)
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
