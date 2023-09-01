package rs.raf.jun_Filip_Novakovic_882020RN.data.repositories

import io.reactivex.Observable
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.SingleStock
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.Stocks

interface StockRepository {
 /*   fun fetchAll(): Observable<Resource<Unit>>*/
    //fun getAll(): Observable<List<Stocks>>
    //fun getById(id:String): Observable<List<UserStocks>>
    //fun add(stock: StocksEntity): Completable
    fun fetchAll(): Observable<List<Stocks>>
    fun getBySymbol(symbol:String): Observable<SingleStock>

}