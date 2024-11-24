package alexandrefournier.topheadlines

import alexandrefournier.topheadlines.ui.viewmodel.ArticleDetailsViewModel
import alexandrefournier.topheadlines.ui.viewmodel.ArticlesViewModel
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    singleOf(::createJson)
    // single { createHttpClient(get(), get()) }
    singleOf(::createHttpClient)
    singleOf(::ApiBridge)
    singleOf(::ArticlesRepositoryImpl) bind ArticlesRepository::class

    viewModelOf(::ArticlesViewModel)
    viewModelOf(::ArticleDetailsViewModel)
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true; prettyPrint = true }
