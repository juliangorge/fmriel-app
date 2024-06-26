package components

import MainPost
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun MainPostCard(post: MainPost, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(top = 20.dp)
            .clickable {
                navController.navigate("postDetail/${post.id}")
            }
    ) {

        if (post.image != null) {
            val imageUrl = "https://rielfm.com.ar/public/files/images/${post.image}"
            Image(
                painter = rememberImagePainter(data = imageUrl),
                contentDescription = post.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9)
                    .padding(bottom = 8.dp),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = post.section,
            style = MaterialTheme.typography.body1,
            color = Color.Red,
            modifier = Modifier.padding(10.dp, 0.dp)
        )

        Text(
            text = post.title,
            style = MaterialTheme.typography.h5,
            color = Color.White,
            modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 20.dp)
        )
    }
}
