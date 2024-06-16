package screens

import MainViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import components.MainPostCard
import components.PostCard

@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val mainPost by mainViewModel.mainPost.collectAsState()
    val posts by mainViewModel.postsList.collectAsState()
    val isLoading by mainViewModel.loading.collectAsState()

    val swipeRefreshState = rememberSwipeRefreshState(isLoading)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            mainViewModel.refreshData()
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
        ) {
            mainPost?.let {
                item { MainPostCard(post = it, navController) }
            }
            items(posts) { post ->
                PostCard(post = post, navController)
            }
        }
    }
}