package alexandrefournier.topheadlines

import alexandrefournier.topheadlines.test.TestTopHeadlinesApp
import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

@Suppress("unused")
class InstrumentationTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        classLoader: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(classLoader, TestTopHeadlinesApp::class.java.name, context)
    }
}