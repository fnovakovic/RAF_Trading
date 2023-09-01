package rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Reccomend(
    val buyRecommendationsPct: String,
    val sellRecommendationsPct: String,
    val holdRecommendationsPct: String
    )