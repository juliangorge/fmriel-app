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

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    init {
        refreshData()
    }

    fun refreshData() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val fetchedMainPost = PostComponent().getMainPost()
                val fetchedPostsList = PostComponent().getLineUp()
                _mainPost.value = fetchedMainPost
                _postsList.value = fetchedPostsList
            } catch (e: Exception) {
                // Handle error if needed
            } finally {
                _loading.value = false
            }
        }
    }
}