package rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks

import com.squareup.moshi.JsonClass
import timber.log.Timber

@JsonClass(generateAdapter = true)
data class SingleStockData(
    val data: /*List<*/SingleStock/*>*/
)


fun SingleStockData.toStocksBySymbol(symbol:String): SingleStock? {

    var stockk: SingleStock? = null
    var s: Stocks
    var metrics :Metrics
    Timber.e("OVO JE LISTAAAAAAAA")
    Timber.e(data.toString())
   // for(i in data){

        /*if(i.symbol.equals(symbol)) {
            metrics = Metrics(
                i.metrics.alpha,i.metrics.beta,i.metrics.sharp,i.metrics.marketCup,i.metrics.eps,i.metrics.ebit
            )
            //}
            stockk =
                SingleStock(
                    i.instrumentId,i.symbol,i.name,i.currency,i.last,i.open,i.close,i.bid,i.ask,metrics,i.chart
                )

            Timber.e("NASAOO GA")

        }*/
    //}
    metrics = Metrics(
        data.metrics.alpha,data.metrics.beta,data.metrics.sharp,data.metrics.marketCup,data.metrics.eps,data.metrics.ebit
    )
    stockk =
        SingleStock(
            data.instrumentId,data.symbol,data.name,data.currency,data.last,data.open,data.close,data.bid,data.ask,
            metrics,data.chart
        )

    Timber.e(stockk.toString())
    return stockk
}

