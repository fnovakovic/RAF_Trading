package rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Metrics(
    val alpha: String,
    val beta: String,
    val sharp: String,
    val marketCup: String,
    val eps: String,
    val ebit: String
)