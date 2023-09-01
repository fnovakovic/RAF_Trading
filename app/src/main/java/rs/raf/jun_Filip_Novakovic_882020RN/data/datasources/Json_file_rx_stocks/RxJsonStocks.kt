package rs.raf.vezbe8.data.datasources.rx

import android.content.Context
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.StocksResponse
import timber.log.Timber
import java.io.IOException

class RxJsonStocks(
    private val context: Context,
    private val moshi: Moshi
    ) : RxStock {

    override fun getUserData(): Observable<StocksResponse> {
        return Observable.create {
            val userJson = getJsonDataFromAsset()
            val jsonAdapter = moshi.adapter(StocksResponse::class.java)
            val userData: StocksResponse = jsonAdapter.fromJson(userJson)!! //Nije dobra praksa
            it.onNext(userData)
            it.onComplete()
        }
    }

    private fun getJsonDataFromAsset(): String? {
        val jsonString: String
        try {
            Timber.e("USAO U Stocks")
            jsonString = context.assets.open("getStocks.json").bufferedReader().use { it.readText() }

            Timber.e(jsonString)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}