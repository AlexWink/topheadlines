package alexandrefournier.topheadlines.ui.screen

import alexandrefournier.topheadlines.R
import alexandrefournier.topheadlines.ui.component.ArticleItemView
import alexandrefournier.topheadlines.ui.viewmodel.ArticlesUiState
import alexandrefournier.topheadlines.ui.viewmodel.ArticlesViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticlesScreen(
    onArticleClicked: (String) -> Unit,
    viewModel: ArticlesViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ArticlesScreen(uiState, viewModel::fetchArticles, onArticleClicked)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesScreen(
    uiState: ArticlesUiState,
    onRefreshClicked: () -> Unit,
    onArticleClicked: (String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                {
                    Text(stringResource(R.string.app_name))
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (uiState) {
                is ArticlesUiState.Loading -> {
                    CircularProgressIndicator(Modifier.testTag("articlesProgressIndicator"))
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
                                modifier = Modifier.testTag("articlesItem$index"),
                                onClicked = {
                                    onArticleClicked(articles[index].id)
                                },
                            )
                        }
                    }
                }

                is ArticlesUiState.Error -> {
                    Text("Error: ${uiState.message}")
                    Button(onRefreshClicked, Modifier.testTag("articlesRefreshButton")) {
                        Text(stringResource(R.string.refresh))
                    }
                }
            }
        }
    }
}
