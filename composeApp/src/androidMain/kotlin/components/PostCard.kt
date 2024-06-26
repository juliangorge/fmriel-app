package components

import Post
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun PostCard(post: Post, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                navController.navigate("postDetail/${post.id}")
            }
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 8.dp)
                .height(1.dp)
                .background(Color.LightGray)
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
        } else {
            Text(
                text = post.summary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Text(
            text = post.pompadour,
            style = MaterialTheme.typography.body1,
        )

        Text(
            text = post.title,
            style = MaterialTheme.typography.h5
        )

    }
}