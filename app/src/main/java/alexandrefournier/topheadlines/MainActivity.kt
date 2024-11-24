package alexandrefournier.topheadlines

import alexandrefournier.topheadlines.ui.screen.ArticleDetailsScreen
import alexandrefournier.topheadlines.ui.screen.ArticlesScreen
import alexandrefournier.topheadlines.ui.theme.TopHeadlinesTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            TopHeadlinesTheme {
                KoinAndroidContext {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Destination.Articles,
                        // modifier = Modifier
                        //     .fillMaxSize()
                    ) {
                        composable<Destination.Articles> {
                            ArticlesScreen(
                                onArticleClicked = { id ->
                                    navController.navigate(
                                        route = Destination.ArticleDetails(
                                            id
                                        )
                                    )
                                }
                            )
                        }
                        composable<Destination.ArticleDetails> {
                            ArticleDetailsScreen {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed interface Destination {
    @Serializable
    object Articles
    @Serializable
    class ArticleDetails(val id: String)
}