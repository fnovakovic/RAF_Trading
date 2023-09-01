package rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.local_user

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.model_user.UserEntity

@Dao
abstract class UserDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: UserEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<UserEntity>): Completable

    @Query("SELECT * FROM users")
    abstract fun getAll(): Observable<List<UserEntity>>

    @Query("SELECT * FROM users where logged = :access")
    abstract fun getByAccess(access: String): Observable<List<UserEntity>>

    @Query("DELETE FROM users")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<UserEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM users WHERE username LIKE :name || '%'")
    abstract fun getByName(name: String): Observable<List<UserEntity>>

    @Query("UPDATE users SET logged = 1 WHERE username LIKE :name || '%'")
    abstract fun updateLogginEncrement(name: String): Completable

    @Query("UPDATE users SET money = 10000,firstTime = 1, portfolioValue = 0  WHERE logged  = :access")
    abstract fun updatePortfolio(access:String): Completable

    @Query("UPDATE users SET logged = 0")
    abstract fun updateLogginDecrement(): Completable


    @Query("UPDATE users SET money = :money where id =:id")
    abstract fun decr(money:String,id:String): Completable

}