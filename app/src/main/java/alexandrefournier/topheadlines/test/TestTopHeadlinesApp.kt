package alexandrefournier.topheadlines.test

import alexandrefournier.topheadlines.ArticlesRepository
import alexandrefournier.topheadlines.appModule
import android.app.Application
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

class TestTopHeadlinesApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule, instrumentedTestModule)
        }
    }
}

val instrumentedTestModule = module {
    singleOf(::TestArticlesRepository) bind ArticlesRepository::class
}