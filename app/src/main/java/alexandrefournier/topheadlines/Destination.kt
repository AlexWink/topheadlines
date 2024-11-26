package alexandrefournier.topheadlines

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    object Articles

    @Serializable
    class ArticleDetails(val id: String)
}