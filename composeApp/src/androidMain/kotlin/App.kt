import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.juliangorge.fmriel.R
import screens.MainScreen
import screens.PostDetailScreen

@Composable
@Preview
fun App(mainViewModel: MainViewModel = viewModel()) {
    MaterialTheme {
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()
        var showMenu by remember { mutableStateOf(false) }

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { navController.navigate("main") },
                        ) {
                            IconButton(onClick = { showMenu = !showMenu }) {
                                Icon(Icons.Default.Menu, contentDescription = "Menu")
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_background), // Asegúrate de tener tu logo en los recursos drawables
                                contentDescription = "Logo",
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("App Title", fontSize = 20.sp, color = Color.Black)
                        }
                    },
                    backgroundColor = Color.White,
                    contentColor = Color.Black
                )
            },
            drawerContent = {
                if (showMenu) {
                    DrawerContent(navController)
                }
            }
        ) { paddingValues ->
            NavHost(navController, startDestination = "main", modifier = Modifier.padding(paddingValues)) {
                composable("main") { MainScreen(navController, mainViewModel) }
                composable("postDetail/{postId}") { backStackEntry ->
                    val postId = backStackEntry.arguments?.getString("postId")
                    PostDetailScreen(navController, postId)
                }
            }
        }
    }
}

@Composable
fun DrawerContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Menu Item 1", modifier = Modifier.padding(8.dp))
        Text("Menu Item 2", modifier = Modifier.padding(8.dp))
        // Agrega más elementos de menú aquí
    }
}