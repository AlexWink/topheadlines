package alexandrefournier.topheadlines.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val title: String?,
    val urlToImage: String?,
    val description: String?,
    val url: String?,
)