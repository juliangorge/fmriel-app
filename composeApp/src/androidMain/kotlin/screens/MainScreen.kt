package screens

import MainViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import components.MainPostCard
import components.PostCard

@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val mainPost by mainViewModel.mainPost.collectAsState()
    val posts by mainViewModel.postsList.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        mainPost?.let {
            item { MainPostCard(post = mainPost!!, navController) }
        }
        items(posts) { post ->
            PostCard(post = post, navController)
        }
    }
}
