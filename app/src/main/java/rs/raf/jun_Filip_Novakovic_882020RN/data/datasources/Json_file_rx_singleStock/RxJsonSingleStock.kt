package rs.raf.vezbe8.data.datasources.rx

import android.content.Context
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.SingleStockData
import timber.log.Timber
import java.io.IOException

class RxJsonSingleStock(
    private val context: Context,
    private val moshi: Moshi
    ) : RxSingleStock {

    override fun getUserData(): Observable<SingleStockData> {/*SingleStockResponse*/
        return Observable.create {
            val userJson = getJsonDataFromAsset()
            val jsonAdapter = moshi.adapter(SingleStockData::class.java)
            val userData: SingleStockData = jsonAdapter.fromJson(userJson)!! //Nije dobra praksa
            it.onNext(userData)
            it.onComplete()
        }
    }

    private fun getJsonDataFromAsset(): String? {
        val jsonString: String
        try {
            Timber.e("USAO U SingleStock")
            jsonString = context.assets.open("getSingleStocks.json").bufferedReader().use { it.readText() }

            Timber.e(jsonString)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}