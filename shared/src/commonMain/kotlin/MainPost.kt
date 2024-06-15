import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class MainPost (
    @SerialName("id")
    val id: Int,

    @SerialName("section")
    val section: String,

    @SerialName("title")
    val title: String,

    @SerialName("image")
    val image: String? = null
)