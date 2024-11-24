package alexandrefournier.topheadlines.ui.screen

import alexandrefournier.topheadlines.ui.viewmodel.ArticlesViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticlesScreen() {
    val viewModel: ArticlesViewModel = koinViewModel()
    ArticlesScreen(viewModel::fetchArticles)
}

@Composable
fun ArticlesScreen(onRefreshClicked: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onRefreshClicked) {
            Text("Refresh")
        }
    }
}