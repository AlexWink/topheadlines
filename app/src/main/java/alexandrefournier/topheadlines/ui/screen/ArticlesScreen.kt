package alexandrefournier.topheadlines.ui.screen

import alexandrefournier.topheadlines.model.Article
import alexandrefournier.topheadlines.ui.viewmodel.ArticlesUiState
import alexandrefournier.topheadlines.ui.viewmodel.ArticlesViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticlesScreen() {
    val viewModel: ArticlesViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ArticlesScreen(uiState, viewModel::fetchArticles)
}

@Composable
fun ArticlesScreen(uiState: ArticlesUiState, onRefreshClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState) {
            is ArticlesUiState.Loading -> {
                Text("Loading articles...")
            }

            is ArticlesUiState.Loaded -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(vertical = 10.dp),
                ) {
                    val articles = uiState.articles
                    items(articles.size) { index ->
                        ArticleItemView(
                            article = articles[index],
                            onClicked = { }
                        )
                    }
                }
            }

            is ArticlesUiState.Error -> {
                Text("Error: ${uiState.message}")
                Button(onRefreshClicked) {
                    Text("Refresh")
                }
            }
        }
    }
}

@Composable
fun ArticleItemView(article: Article, onClicked: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClicked),
        tonalElevation = 30.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = "Article image",
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(200.dp),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = article.title ?: "No title",
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}