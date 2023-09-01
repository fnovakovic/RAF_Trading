package rs.raf.jun_Filip_Novakovic_882020RN.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.model_user.User

interface UserRepository {

    fun getAll(): Observable<List<User>>
    fun getByAccess(access: String): Observable<List<User>>
    fun getAllByName(name: String): Observable<List<User>>
    fun insert(user: User): Completable
    fun porfolioUpdate(access:String): Completable
    fun update(user: String): Completable

    fun updateDecr(): Completable
    fun decr(money:String,id:String): Completable
}