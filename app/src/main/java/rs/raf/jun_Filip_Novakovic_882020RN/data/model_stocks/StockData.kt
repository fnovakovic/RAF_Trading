package rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks

import com.squareup.moshi.JsonClass
import timber.log.Timber

@JsonClass(generateAdapter = true)
data class StockData(
    val quotes: List<Stocks>
)

fun StockData.toStocks(): List<Stocks> {
    var temp: ArrayList<Stocks> = ArrayList()
    var recomendations :Reccomend
    //var lista: List<News> = listOf()
   // var d: Int = 0;
    Timber.e("OVO JE LISTA")
    Timber.e(quotes.toString())
    for(i in quotes){
        //lista.toMutableList().add(d++,News(i.title,i.content,i.link,i.date,i.image))
        //for(i2 in i.recommendation) {
             //recomendations = Reccomend(i2.buyRecommendationsPct,i2.sellRecommendationsPct,i2.holdRecommendationsPct)
             recomendations = Reccomend(i.recommendation.buyRecommendationsPct,i.recommendation.sellRecommendationsPct,i.recommendation.holdRecommendationsPct)
        //}
        temp.add(Stocks(i.instrumentId,i.symbol,i.name,i.currency,i.last,i.changeFromPreviousClose,i.percentChangeFromPreviousClose,i.marketName,recomendations,i.chart))
    }
    return temp
}
/*fun StockData.toStocksBySymbol(symbol:String): Stocks? {
    //var temp: ArrayList<Stocks> = ArrayList()
    var stockk: Stocks? = null
    var s: Stocks
    var recomendations :Reccomend
    //var lista: List<News> = listOf()
    // var d: Int = 0;
    Timber.e("OVO JE LISTA")
    Timber.e(quotes.toString())
    for(i in quotes){

        if(i.symbol.equals(symbol)) {
            recomendations = Reccomend(
                i.recommendation.buyRecommendationsPct,
                i.recommendation.sellRecommendationsPct,
                i.recommendation.holdRecommendationsPct
            )
            //}
             stockk =
                Stocks(
                    i.instrumentId,
                    i.symbol,
                    i.name,
                    i.currency,
                    i.last,
                    i.changeFromPreviousClose,
                    i.percentChangeFromPreviousClose,
                    recomendations,
                    i.chart
                )

        }
    }
    return stockk
}*/

