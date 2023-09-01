package rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks

import com.squareup.moshi.JsonClass
import timber.log.Timber

@JsonClass(generateAdapter = true)
data class ChartsData(
    val bars: List<Charts>
)

fun ChartsData.toNews(): List<Charts> {
    var temp: ArrayList<Charts> = ArrayList()
    //var lista: List<News> = listOf()
    // var d: Int = 0;
    Timber.e("OVO JE LISTA")
    Timber.e(bars.toString())
    for(i in bars){
        //lista.toMutableList().add(d++,News(i.title,i.content,i.link,i.date,i.image))
        temp.add(Charts(i.price,i.time))
    }
    return temp
}