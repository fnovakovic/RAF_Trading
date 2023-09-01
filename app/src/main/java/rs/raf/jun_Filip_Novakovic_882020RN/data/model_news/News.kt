package rs.raf.jun_Filip_Novakovic_882020RN.data.model_news

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class News(
    val title: String,
    val content: String,
    val link: String,
    val date: String,
    val image: String
)