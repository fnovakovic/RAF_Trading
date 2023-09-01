package rs.raf.vezbe8.data.datasources.rx

import android.content.Context
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.model2.NewsResponse
import timber.log.Timber
import java.io.IOException

class RxJsonNews(
    private val context: Context,
    private val moshi: Moshi
    ) : RxNews {

    override fun getUserData(): Observable<NewsResponse> {
        return Observable.create {
            val userJson = getJsonDataFromAsset()
            val jsonAdapter = moshi.adapter(NewsResponse::class.java)
            val userData: NewsResponse = jsonAdapter.fromJson(userJson)!! //Nije dobra praksa
            it.onNext(userData)
            it.onComplete()
        }
    }

    private fun getJsonDataFromAsset(): String? {
        val jsonString: String
        try {
            Timber.e("USAO U NEWS")
            jsonString = context.assets.open("getNews.json").bufferedReader().use { it.readText() }

            Timber.e(jsonString)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}