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
    singleOf(::createHttpClient)
    singleOf(::ApiBridge)

    viewModelOf(::ArticlesViewModel)
    viewModelOf(::ArticleDetailsViewModel)
}

val productionModule = module {
    singleOf(::ArticlesRepositoryImpl) bind ArticlesRepository::class
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true; prettyPrint = true }
