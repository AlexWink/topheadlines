package alexandrefournier.topheadlines.model

import kotlinx.serialization.Serializable

@Serializable
data class TopHeadlinesDto(
    val articles: List<Article>
)