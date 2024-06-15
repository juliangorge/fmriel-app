import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class PostComponent {

    private val apiURL = "https://rielfm.com.ar/api"

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getMainPost() : MainPost {
        val response: HttpResponse = httpClient.get("$apiURL/getMainPost")
        val mainPostResponse: MainPostResponse = Json.decodeFromString(response.bodyAsText())
        return mainPostResponse.posts
    }

    suspend fun getLineUp(): List<Post> {
        val posts: List<Post> = httpClient.get("$apiURL/getPosts").body()
        return posts
    }

    suspend fun getPostById(postId: String): Post {
        val posts: List<Post> = httpClient.get("$apiURL/getPostBy/$postId").body()
        return posts[0]
    }
}