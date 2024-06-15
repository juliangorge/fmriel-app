import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainPostResponse(

    @SerialName("posts")
    val posts: MainPost,

    @SerialName("shedule")
    val shedule: Schedule
)

@Serializable
data class Schedule(
    val schedule: Int
)