package rs.raf.jun_Filip_Novakovic_882020RN.data.repositories

import android.annotation.SuppressLint
import io.reactivex.Observable
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.*
import rs.raf.vezbe8.data.datasources.rx.RxStock
import rs.raf.vezbe8.data.datasources.rx.RxSingleStock

class StocksRepositoryImpl(

    private val remoteDataSource: RxStock,
    private val remoteDataSource3: RxSingleStock
) : StockRepository {

   @SuppressLint("CheckResult")
   override fun fetchAll(): Observable<List<Stocks>>  { //Observable<Resource<Unit>>

       return remoteDataSource
           .getUserData()
           .map {
               it.data.toStocks()
           }

   }
    override fun getBySymbol(symbol:String): Observable<SingleStock> {
        return remoteDataSource3
            .getUserData()
            .map {
                it.toStocksBySymbol(symbol)

            }
    }

}