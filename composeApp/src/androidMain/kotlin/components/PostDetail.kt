package components

import Post
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter

@Composable
fun PostDetail(post: Post) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp)
    ) {

        Text(
            text = post.title,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = post.pompadour,
            style = MaterialTheme.typography.body1,
        )

        Text(
            text = post.summary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (post.image != null) {
            val imageUrl = "https://rielfm.com.ar/public/files/images/${post.image}"

            Image(
                painter = rememberImagePainter(data = imageUrl),
                contentDescription = post.epigraph,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9)
                    .padding(bottom = 8.dp),
                contentScale = ContentScale.Crop
            )
        }

        post.body?.let {
            HTMLContent(it)
        }
    }
}

@Composable
fun HTMLContent(htmlContent: String) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp) // Ajusta la altura según sea necesario
    )
}