package rs.raf.jun_Filip_Novakovic_882020RN.data.repositories

import android.annotation.SuppressLint
import io.reactivex.Observable
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_news.News
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_news.toNews
import rs.raf.vezbe8.data.datasources.rx.RxNews


class NewsRepositoryImpl(
//    private val remoteDataSource: NewsService/*,
//    private val localDataSource: StocksFetchDao*/
    private val remoteDataSource: RxNews

) : NewsRepository {
    private var i: Int = 0;
    @SuppressLint("CheckResult")
    override fun fetchAll(): Observable<List<News>>  { //Observable<Resource<Unit>>

        return remoteDataSource
            .getUserData()
            .map {
                it.data.toNews()
            }

    }

}