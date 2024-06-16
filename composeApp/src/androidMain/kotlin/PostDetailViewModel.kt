import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostDetailViewModel : ViewModel() {
    private val _post = MutableStateFlow<Post?>(null)
    val post: StateFlow<Post?> get() = _post

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    fun getPostById(postId: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val fetchedPost = PostComponent().getPostById(postId)
                _post.value = fetchedPost
            } catch (e: Exception) {
                // Handle error if needed
            } finally {
                _loading.value = false
            }
        }
    }
}