package rs.raf.jun_Filip_Novakovic_882020RN.data.model_news

import com.squareup.moshi.JsonClass
import timber.log.Timber

@JsonClass(generateAdapter = true)
data class NewsData(
    val newsItems: List<News>
)

fun NewsData.toNews(): List<News> {
    var temp: ArrayList<News> = ArrayList()

    Timber.e("USAO U NEWSDATA I OVO JE LISTA")
    Timber.e(newsItems.toString())
    for(i in newsItems){
        temp.add(News(i.title,i.content,i.link,i.date,i.image))
    }
    return temp
}



