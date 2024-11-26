package alexandrefournier.topheadlines

import alexandrefournier.topheadlines.model.Article
import alexandrefournier.topheadlines.model.TopHeadlinesDto

interface ArticlesRepository {
    var articles: List<Article>
    suspend fun fetchArticles(): Result<TopHeadlinesDto>
    fun findArticleById(id: String): Article?
}

class ArticlesRepositoryImpl(private val apiBridge: ApiBridge) : ArticlesRepository {
    override var articles: List<Article> = emptyList()

    override suspend fun fetchArticles(): Result<TopHeadlinesDto> {
        val sourcesResult = apiBridge.findSources()
        return sourcesResult.fold(
            onSuccess = { sources ->
                return@fold apiBridge.getTopHeadlines(sources.sources.filterNot { it.id.contains("google-news") })
                    .onSuccess { articles = it.articles }
            }
        ) {
            Result.failure(it)
        }
    }

    override fun findArticleById(id: String) = articles.find { it.id == id }
}
