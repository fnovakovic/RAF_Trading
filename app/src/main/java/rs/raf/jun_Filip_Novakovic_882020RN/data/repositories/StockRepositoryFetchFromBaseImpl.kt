package rs.raf.jun_Filip_Novakovic_882020RN.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.local_stocks.StocksDao
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.StocksEntity
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.UserStocks
import timber.log.Timber

class StockRepositoryFetchFromBaseImpl(
    private val localDataSource: StocksDao

) : StockRepositoryFetchFromBase {

    override fun getById(id: String): Observable<List<UserStocks>> {
        Timber.e("DESAVA SE")
        return localDataSource
            .getById(id)
            .map {
                Timber.e("DESAVA SE2")
                it.map {
                    Timber.e("DESAVA SE3")
                    UserStocks(it.id, it.name,it.userId,it.quantity,it.symbol,it.price)
                }
            }
    }

    override fun add(stock: StocksEntity): Completable {
        /*val userEntity = StocksEntity("","","","")*/
        return localDataSource
            .insert(stock)
    }
    override fun delete(name:String,id:String): Completable {

        return localDataSource
            .delete(name,id)
    }

    override fun update(userId: String,name:String,qty:String): Completable {
        return localDataSource
            .update(userId,name,qty)
    }

    override fun getByIdAndName(id: String,name:String): Observable<List<UserStocks>> {
        Timber.e("DOHVATANJE USER STOCK DATA LEVEL 1 GETBYIDANDNAME METODA")
        return localDataSource
            .getByIdAndName(id,name)
            .map {

                it.map {
                Timber.e(" MAP  U METODI GETBYIDANDNAME DOHVATANJE USER STOCK DATA LEVEL 1")
                    UserStocks(it.id, it.name,it.userId,it.quantity,it.symbol,it.price)
                }
            }
    }


}