package alexandrefournier.topheadlines.ui.component

import alexandrefournier.topheadlines.model.Article
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun ArticleItemView(article: Article, modifier: Modifier, onClicked: () -> Unit) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClicked),
        tonalElevation = 30.dp,
        shadowElevation = 3.dp,
        shape = RoundedCornerShape(8.dp)
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