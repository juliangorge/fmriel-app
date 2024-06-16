package screens

import PostDetailViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import components.PostDetail

@Composable
fun PostDetailScreen(navController: NavHostController, postId: String?) {
    val postDetailViewModel: PostDetailViewModel = viewModel()

    LaunchedEffect(postId) {
        postId?.let {
            postDetailViewModel.getPostById(it)
        }
    }

    val post by postDetailViewModel.post.collectAsState()
    val isLoading by postDetailViewModel.loading.collectAsState()
    val isRefreshing by remember { mutableStateOf(false) }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    LaunchedEffect(isLoading) {
        if (!isLoading) {
            swipeRefreshState.isRefreshing = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                postId?.let {
                    swipeRefreshState.isRefreshing = true
                    postDetailViewModel.getPostById(it)
                }
            },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    contentColor = MaterialTheme.colors.primary
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                post?.let {
                    item { PostDetail(post = it) }
                }
            }
        }

        if (isLoading && !swipeRefreshState.isRefreshing) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}