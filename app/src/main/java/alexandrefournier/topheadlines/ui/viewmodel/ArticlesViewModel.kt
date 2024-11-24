package alexandrefournier.topheadlines.ui.viewmodel

import alexandrefournier.topheadlines.ArticlesRepository
import alexandrefournier.topheadlines.model.Article
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(private val repository: ArticlesRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<ArticlesUiState>(ArticlesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchArticles()
    }

    fun fetchArticles() {
        viewModelScope.launch {
            repository.fetchArticles().onSuccess {
                val articles = it.articles
                _uiState.value = ArticlesUiState.Loaded(articles)
            }.onFailure {
                val errorMessage = it.message ?: "Unknown error occurred"
                _uiState.value = ArticlesUiState.Error(errorMessage)
            }
        }
    }
}

sealed interface ArticlesUiState {
    data object Loading : ArticlesUiState
    data class Loaded(val articles: List<Article>) : ArticlesUiState
    data class Error(val message: String) : ArticlesUiState
}
