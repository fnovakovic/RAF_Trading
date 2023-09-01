package rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.jun_Filip_Novakovic_882020RN.data.repositories.StockRepository
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.contract.MainContract
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.sigleStockState.AddSingleStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.sigleStockState.SingleStockState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_stocks.AddStocksState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_stocks.StockState
import timber.log.Timber

class StocksViewModel(
    private val subjectRepository: StockRepository,
) : ViewModel(), MainContract.ViewModelStocks {

    private val subscriptions = CompositeDisposable()

    override val stockState: MutableLiveData<StockState> = MutableLiveData()
    override val addDone: MutableLiveData<AddStocksState> = MutableLiveData()
    override val singleStockState: MutableLiveData<SingleStockState> = MutableLiveData()
    override val addDone2: MutableLiveData<AddSingleStockState> = MutableLiveData()

    override fun getStockBySymbol(symbol:String) {
        Timber.e("METODA GETSTOCKBYSYMBOL ")
        val subscription = subjectRepository
            .getBySymbol(symbol)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    singleStockState.value = SingleStockState.Success(it)
                    Timber.e("SUBSCRIBER U METODI GETSTOCKBYSYMBOL ")

                },
                {
                    singleStockState.value = SingleStockState.Error("ERROR HAPENED WHEN FETCHING SINGLE STOCK DATA FROM BASE")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }


    override fun fetchAllMovies() {
        val subscription = subjectRepository
            .fetchAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    stockState.value = StockState.Success(it)
                    Timber.e("OVO JE VIEW MODEL")
                    //Timber.e(it.toString())

                },
                {
                    stockState.value = StockState.Error("Error happened while fetching data from the server")
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