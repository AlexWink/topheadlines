package alexandrefournier.topheadlines.ui.screen

import alexandrefournier.topheadlines.ui.viewmodel.ArticleDetailsUiState
import alexandrefournier.topheadlines.ui.viewmodel.ArticleDetailsViewModel
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticleDetailsScreen(onBackClicked: () -> Unit) {
    val viewModel: ArticleDetailsViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ArticleDetailsScreen(uiState, onBackClicked)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailsScreen(uiState: ArticleDetailsUiState, onBackClicked: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(title = {
                Text("News details")
            }, navigationIcon = {
                IconButton(onBackClicked) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back button"
                    )
                }
            }, scrollBehavior = scrollBehavior
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (uiState) {
                is ArticleDetailsUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is ArticleDetailsUiState.Loaded -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        val article = uiState.article
                        AsyncImage(
                            model = article.urlToImage,
                            contentDescription = "Article image",
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .fillMaxWidth(),
                            contentScale = ContentScale.FillWidth,
                        )

                        Text(
                            text = article.title ?: "Title",
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Text(
                            text = article.description ?: "Description",
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Row {
                            val context = LocalContext.current
                            FilledIconButton(onClick = {
                                article.url?.let { url ->
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                    context.startActivity(intent)
                                }
                            }) {
                                Icon(Icons.Default.Search, contentDescription = "Search url")
                            }
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = article.url ?: "url",
                                style = MaterialTheme.typography.bodySmall,
                            )
                        }
                    }
                }

                is ArticleDetailsUiState.Error -> {
                    Text(uiState.message)
                    Button(onBackClicked) {
                        Text("Back")
                    }
                }
            }
        }
    }
}