import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _mainPost = MutableStateFlow<MainPost?>(null)
    val mainPost: StateFlow<MainPost?> get() = _mainPost

    private val _postsList = MutableStateFlow<List<Post>>(listOf())
    val postsList: StateFlow<List<Post>> get() = _postsList

    init {
        viewModelScope.launch {
            PostComponent().getMainPost().let { _mainPost.value = it }
            PostComponent().getLineUp().let { _postsList.value = it }
        }
    }
}