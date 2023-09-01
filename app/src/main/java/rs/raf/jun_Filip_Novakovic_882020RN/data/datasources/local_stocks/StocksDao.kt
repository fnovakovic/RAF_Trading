package rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.local_stocks

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.StocksEntity


@Dao
abstract class StocksDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: StocksEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<StocksEntity>): Completable

    @Query("SELECT * FROM stocks")
    abstract fun getAll(): Observable<List<StocksEntity>>

    @Query("DELETE FROM stocks")
    abstract fun deleteAll()

    @Query("DELETE FROM stocks where userId = :id and name = :name")
    abstract fun delete(name:String,id:String): Completable

    @Query("SELECT * from stocks WHERE userId = :userId")
    abstract fun getById(userId: String): Observable<List<StocksEntity>>

    @Query("SELECT * from stocks WHERE userId = :userId and name = :name")
    abstract fun getByIdAndName(userId: String,name:String): Observable<List<StocksEntity>>

    @Query("UPDATE stocks SET quantity = :qty WHERE userId =:userId and name = :name")
    abstract fun update(userId: String,name:String,qty:String): Completable

    @Transaction
    open fun deleteAndInsertAll(entities: List<StocksEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }


}