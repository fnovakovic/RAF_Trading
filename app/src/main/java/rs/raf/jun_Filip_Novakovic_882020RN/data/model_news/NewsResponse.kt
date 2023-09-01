package rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.model2

import com.squareup.moshi.JsonClass
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_news.NewsData

@JsonClass(generateAdapter = true)
data class NewsResponse(
    val data:NewsData

)