package alexandrefournier.topheadlines.ui.viewmodel

import alexandrefournier.topheadlines.ArticlesRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ArticlesViewModel(private val repository: ArticlesRepository) : ViewModel() {

    fun fetchArticles() {
        viewModelScope.launch {
            repository.fetchArticles()
        }
    }
}
