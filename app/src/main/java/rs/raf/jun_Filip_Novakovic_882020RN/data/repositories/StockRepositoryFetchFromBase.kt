package rs.raf.jun_Filip_Novakovic_882020RN.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.StocksEntity
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.UserStocks

interface StockRepositoryFetchFromBase {

    fun getById(id:String): Observable<List<UserStocks>>
    fun getByIdAndName(id:String,name:String): Observable<List<UserStocks>>
    fun update(userId: String,name:String,qty:String): Completable
    fun add(stock: StocksEntity):Completable
    fun delete(name: String,id:String): Completable

}