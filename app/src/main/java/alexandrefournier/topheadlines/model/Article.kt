package alexandrefournier.topheadlines.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Article(
    val id: String = UUID.randomUUID().toString(),
    val title: String?,
    val urlToImage: String?,
    val description: String?,
    val url: String?,
)