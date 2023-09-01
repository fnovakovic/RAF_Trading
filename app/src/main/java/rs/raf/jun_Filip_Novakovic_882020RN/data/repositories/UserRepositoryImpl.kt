package rs.raf.jun_Filip_Novakovic_882020RN.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.local_user.UserDao
import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.model_user.User
import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.model_user.UserEntity
import timber.log.Timber

class UserRepositoryImpl(
    private val localDataSource: UserDao

) : UserRepository {
    private var i: Int = 0;

    override fun getAll(): Observable<List<User>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    User(it.id, it.username,it.password,it.email,it.logged,it.money,it.firstTime,it.portfolioValue)
                }
            }
    }

    override fun getByAccess(access: String): Observable<List<User>> {
        Timber.e("USAOO12")
        return localDataSource
            .getByAccess(access)
            .map {
                it.map {
                    User(it.id, it.username,it.password,it.email,it.logged,it.money,it.firstTime,it.portfolioValue)
                }
            }
    }

    override fun getAllByName(name: String): Observable<List<User>> {
        return localDataSource
            .getByName(name)
            .map {
                it.map {
                    User(it.id, it.username,it.password,it.email,it.logged,it.money,it.firstTime,it.portfolioValue)
                }
            }
    }


    override fun insert(note: User): Completable {
        val userEntity = UserEntity(i++.toString(), "", "","","","","","")
        return localDataSource
            .insert(userEntity)
    }



    override fun porfolioUpdate(access:String): Completable {
        return localDataSource
            .updatePortfolio(access)
    }


    override fun update(user: String): Completable {
        return localDataSource
            .updateLogginEncrement(user)
    }

    override fun updateDecr(): Completable {
        return localDataSource
            .updateLogginDecrement()
    }

    override fun decr(money:String,id:String): Completable {
        return localDataSource
            .decr(money,id)
    }

}