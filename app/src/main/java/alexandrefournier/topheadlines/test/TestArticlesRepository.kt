package alexandrefournier.topheadlines.test

import alexandrefournier.topheadlines.ArticlesRepository
import alexandrefournier.topheadlines.model.Article
import alexandrefournier.topheadlines.model.TopHeadlinesDto
import kotlinx.coroutines.delay

class TestArticlesRepository : ArticlesRepository {
    override var articles: List<Article> = sampleArticlesResources

    var fetchResult = Result.success(
        TopHeadlinesDto(
            articles = articles
        )
    )

    override suspend fun fetchArticles(): Result<TopHeadlinesDto> {
        delay(FETCH_DELAY)
        return fetchResult
    }

    override fun findArticleById(id: String): Article? = articles.find { it.id == id }

    companion object {
        const val FETCH_DELAY = 500L

        val sampleArticlesResources = listOf(
            Article(
                id = "testId",
                title = "testTitle",
                urlToImage = "testUrlToImage",
                description = "testDescription",
                url = "testUrl"
            )
        )
    }
}