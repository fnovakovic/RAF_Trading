package rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stocks(
    val instrumentId: String,
    val symbol: String,
    val name: String,
    val currency: String,
    val last: String,
    val changeFromPreviousClose: String,
    val percentChangeFromPreviousClose: String,
    val marketName: String,
    val recommendation: Reccomend,
    val chart: ChartsData

)