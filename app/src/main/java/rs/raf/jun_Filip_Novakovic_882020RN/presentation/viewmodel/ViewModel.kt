package rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.model_user.User
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.StocksEntity
import rs.raf.jun_Filip_Novakovic_882020RN.data.repositories.StockRepositoryFetchFromBase
import rs.raf.jun_Filip_Novakovic_882020RN.data.repositories.UserRepository
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.contract.MainContract
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user_stock.AddUserStocksState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user_stock.UserStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.sigleStockState.AddSingleStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.sigleStockState.SingleStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user.AddUserState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user.UserState
import timber.log.Timber

class ViewModel(
    private val userRepository: UserRepository,
    private val stockRepositoryFromBase: StockRepositoryFetchFromBase,

) : ViewModel(), MainContract.ViewModel3 {

    private val subscriptions = CompositeDisposable()

    override val userState: MutableLiveData<UserState> = MutableLiveData()
    override val addUserDone: MutableLiveData<AddUserState> = MutableLiveData()
    override val stockState: MutableLiveData<UserStockState> = MutableLiveData()
    override val addDone1: MutableLiveData<AddUserStocksState> = MutableLiveData()
    override val singleStockState: MutableLiveData<SingleStockState> = MutableLiveData()
    override val addDone3: MutableLiveData<AddSingleStockState> = MutableLiveData()


    override fun addStockToUser(stock: StocksEntity) {

        Timber.e("OVDE DODAJEMO NOVI STOCK USERU PRVI PUT METODA ADDSTOCKTOUSER")
        val subscription = stockRepositoryFromBase
            .add(stock)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone1.value = AddUserStocksState.Success
                    Timber.e("OVDE DODAJEMO NOVI STOCK USERU PRVI PUT SUCCESS")
                },
                {
                    addDone1.value = AddUserStocksState.Error("ERROR HAPENED WHEN ADDING STOCK TO USER")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getByIdAndName(id:String,name:String) {
        Timber.e("METODA GETBYIDANDNAME DOVLACIMO STOCK KOJI TAJ USER IMA ")
        val subscription = stockRepositoryFromBase
            .getByIdAndName(id,name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("SUBSCRIBER U METODI GETBYIDANDNAME LOADING ")
                    stockState.value = UserStockState.Loading(it)
                },
                {
                    stockState.value = UserStockState.Error("ERROR HAPENED WHEN FETCHING USER STOCK DATA FROM BASE")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun update(id: String, name: String, qty: String) {

        Timber.e("OVDE RADIMO UPDATE USERA TJ DODAJEMO QTY")
        val subscription = stockRepositoryFromBase
            .update(id,name,qty)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //addUserDone.value = AddUserState.Success
                    Timber.e("OVDE RADIMO UPDATE USERA SUBSCRIBER")
                },
                {
                    //addUserDone.value = AddUserState.Error("Error happened while adding movie")
                    //Timber.e(it)
                    Timber.e("ERROR HAPENED WHEN UPDATING USER")
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllUsers() {
        val subscription = userRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userState.value = UserState.Success(it)
                },
                {
                    userState.value = UserState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun updateUser(usr: String) {
        val subscription = userRepository
            .update(usr)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //addUserDone.value = AddUserState.Success
                    Timber.e("USER updated")
                },
                {
                    //addUserDone.value = AddUserState.Error("Error happened while adding movie")
                    //Timber.e(it)
                    Timber.e("ERRORR hapened")
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateUserDecr() {
        val subscription = userRepository
            .updateDecr()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //addUserDone.value = AddUserState.Success
                    Timber.e("USER updated")
                },
                {
                    //addUserDone.value = AddUserState.Error("Error happened while adding movie")
                    //Timber.e(it)
                    Timber.e("ERRORR hapened")
                }
            )
        subscriptions.add(subscription)
    }

    override fun delete(name:String,id:String) {
        val subscription = stockRepositoryFromBase
            .delete(name,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //addUserDone.value = AddUserState.Success
                    Timber.e("USER updated")
                },
                {
                    //addUserDone.value = AddUserState.Error("Error happened while adding movie")
                    //Timber.e(it)
                    Timber.e("ERRORR hapened")
                }
            )
        subscriptions.add(subscription)
    }

    override fun decr(money:String,id:String) {
        Timber.e("OVDE RADIMO DEKREMENTIRAMO NOVAC USERU METODA DECR")
        val subscription = userRepository
            .decr(money,id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //addUserDone.value = AddUserState.Success
                    Timber.e("OVDE RADIMO DEKREMENTIRAMO NOVAC USERU SUBSCRIBE")
                },
                {
                    //addUserDone.value = AddUserState.Error("Error happened while adding movie")
                    //Timber.e(it)
                    Timber.e("ERRORR HAPENED WHEN DECREMENTING MONEY TO USER")
                }
            )
        subscriptions.add(subscription)
    }

    override fun getByAccess(access: String) {
        Timber.e("METODA GETBYACCES DOVLACIMO USERA ")
        val subscription = userRepository
            .getByAccess(access)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userState.value = UserState.Success(it)
                    Timber.e("METODA GETBYACCES DOVLACIMO USERA SUBSCRIBER SUCCESS ")
                },
                {
                    userState.value = UserState.Error("ERROR HAPENED WHEN FETCHING USER DATA FROM BASE")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    override fun getByAccess2(access: String) {
        Timber.e("METODA GETBYACCES DOVLACIMO USERA ")
        val subscription = userRepository
            .getByAccess(access)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userState.value = UserState.DataFetched(it)
                    Timber.e("METODA GETBYACCES DOVLACIMO USERA  SUBSCRIBER DATA FETCH")
                },
                {
                    userState.value = UserState.Error("ERROR HAPENED WHEN FETCHING USER DATA FROM BASE")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getById(id: String) {
        val subscription = stockRepositoryFromBase
            .getById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    stockState.value = UserStockState.Success(it)
                },
                {
                    stockState.value = UserStockState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
    /*override fun getNamesStocks(id: String) {

        val subscription = stockRepositoryFromBase
            .getById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    stockState.value = UserStockState.Success(it)
                },
                {
                    stockState.value = UserStockState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }*/

    override fun portfolioUpdate(access:String) {
        val subscription = userRepository
            .porfolioUpdate(access)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addUserDone.value = AddUserState.Success
                },
                {
                    addUserDone.value = AddUserState.Error("Error happened while adding movie")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getUserByName(name: String){
        val subscription = userRepository
            .getAllByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userState.value = UserState.DataFetched(it)
                },
                {
                    userState.value = UserState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun addUser(usr: User) {
        val subscription = userRepository
            .insert(usr)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addUserDone.value = AddUserState.Success
                },
                {
                    addUserDone.value = AddUserState.Error("Error happened while adding movie")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}