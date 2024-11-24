package alexandrefournier.topheadlines

import alexandrefournier.topheadlines.model.TopHeadlinesDto

interface ArticlesRepository {
    suspend fun fetchArticles(): Result<TopHeadlinesDto>
}

class ArticlesRepositoryImpl(private val apiBridge: ApiBridge) : ArticlesRepository {
    override suspend fun fetchArticles(): Result<TopHeadlinesDto> {
        val sources = apiBridge.findSources()
        return sources.fold({ return@fold apiBridge.getTopHeadlines(it.sources) }) {
            Result.failure(
                it
            )
        }
    }
}
