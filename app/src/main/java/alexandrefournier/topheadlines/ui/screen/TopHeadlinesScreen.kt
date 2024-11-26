package alexandrefournier.topheadlines.ui.screen

import alexandrefournier.topheadlines.Destination
import alexandrefournier.topheadlines.ui.theme.TopHeadlinesTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.androidx.compose.KoinAndroidContext


@Composable
fun TopHeadlinesScreen() {
    TopHeadlinesTheme {
        KoinAndroidContext {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Destination.Articles,
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
                composable<Destination.ArticleDetails> { backStackEntry ->
                    val articleId = backStackEntry.toRoute<Destination.ArticleDetails>().id
                    ArticleDetailsScreen(articleId) {
                        navController.popBackStack()
                    }
                }
            }
        }
    }
}