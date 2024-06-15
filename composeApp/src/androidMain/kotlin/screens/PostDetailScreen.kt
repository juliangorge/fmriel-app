package screens

import PostDetailViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import components.MainPostCard
import components.PostCard
import components.PostDetail

@Composable
fun PostDetailScreen (navController: NavHostController, postId: String?) {
    val postDetailViewModel: PostDetailViewModel = viewModel()

    LaunchedEffect(postId) {
        postId?.let {
            postDetailViewModel.getPostById(it)
        }
    }

    val post by postDetailViewModel.post.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        post?.let {
            item { PostDetail(post = post!!) }
        }
    }
}