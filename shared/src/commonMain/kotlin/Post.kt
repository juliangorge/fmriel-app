import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class Post (
    @SerialName("id")
    val id: Int,

    @SerialName("pompadour")
    val pompadour: String,

    @SerialName("title")
    val title: String,

    @SerialName("image")
    val image: String? = null,

    @SerialName("summary")
    val summary: String,

    @SerialName("epigraph")
    val epigraph: String? = null,

    @SerialName("body")
    val body: String? = null
)