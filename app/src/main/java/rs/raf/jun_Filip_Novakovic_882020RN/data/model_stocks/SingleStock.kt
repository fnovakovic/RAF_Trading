package rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SingleStock(
    val instrumentId: String,
    val symbol: String,
    val name: String,
    val currency: String,
    val last: String,
    val open: String,
    val close: String,
    val bid: String,
    val ask: String,
    val metrics: Metrics,
    val chart: ChartsData

)