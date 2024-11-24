package alexandrefournier.topheadlines

import alexandrefournier.topheadlines.model.TopHeadlinesDto

interface ArticlesRepository {
    suspend fun fetchArticles(): Result<TopHeadlinesDto>
}

class ArticlesRepositoryImpl(private val apiBridge: ApiBridge) : ArticlesRepository {
    override suspend fun fetchArticles(): Result<TopHeadlinesDto> {
        val sourcesResult = apiBridge.findSources()
        return sourcesResult.fold(onSuccess =
        { sources ->
            return@fold apiBridge.getTopHeadlines(sources.sources.filterNot { it.id.contains("google-news") })
        }
        ) {
            Result.failure(
                it
            )
        }
    }
}
