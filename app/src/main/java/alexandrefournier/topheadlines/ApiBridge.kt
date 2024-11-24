package alexandrefournier.topheadlines

import alexandrefournier.topheadlines.model.SourceDto
import alexandrefournier.topheadlines.model.SourcesDto
import alexandrefournier.topheadlines.model.TopHeadlinesDto
import android.util.Log
import androidx.compose.ui.util.fastJoinToString
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import java.util.Locale

private const val baseUrl = "https://newsapi.org/v2"

fun createHttpClient(json: Json) = HttpClient {
    install(ContentNegotiation) {
        json(json)
    }
    install(Logging) {
        logger = Logger.ANDROID
        level = LogLevel.ALL
    }
}

class ApiBridge(private val client: HttpClient) : KoinComponent {

    suspend fun getTopHeadlines(sources: List<SourceDto>) = client.safeGetRequest<TopHeadlinesDto> {
        buildDefaultRequest("top-headlines?sources=${sources.map { it.id }.fastJoinToString(",")}")
    }

    suspend fun findSources() = client.safeGetRequest<SourcesDto> {
        buildDefaultRequest("top-headlines/sources?language=${Locale.getDefault().language}")
    }

    private suspend inline fun <reified T> HttpClient.safeGetRequest(
        block: HttpRequestBuilder.() -> Unit,
    ): Result<T> {
        return try {
            val response = get { block() }
            Result.success(response.body())
        } catch (e: Exception) {
            Log.e("Api Error", e.message ?: e.toString())
            Result.failure(e)
        }
    }


    private fun HttpRequestBuilder.buildDefaultRequest(
        urlExtension: String,
    ) {
        headers[HttpHeaders.Authorization] = BuildConfig.NEWS_API_KEY
        url("$baseUrl/$urlExtension")
    }
}