package alexandrefournier.topheadlines

import alexandrefournier.topheadlines.ui.screen.ArticlesScreen
import alexandrefournier.topheadlines.ui.theme.TopHeadlinesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            TopHeadlinesTheme {
                KoinAndroidContext {
                    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .nestedScroll(scrollBehavior.nestedScrollConnection),
                        topBar = {
                            TopAppBar(
                                { Text("News") },
                                scrollBehavior = scrollBehavior
                            )
                        }
                    ) { innerPadding ->
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            ArticlesScreen()
                        }
                    }
                }
            }
        }
    }
}
