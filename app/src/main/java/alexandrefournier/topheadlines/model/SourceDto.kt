package alexandrefournier.topheadlines.model

import kotlinx.serialization.Serializable

@Serializable
data class SourcesDto(
    val sources: List<SourceDto>
)

@Serializable
data class SourceDto(
    val id: String
)