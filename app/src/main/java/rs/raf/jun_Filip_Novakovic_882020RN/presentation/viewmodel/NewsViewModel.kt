package rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.jun_Filip_Novakovic_882020RN.data.repositories.NewsRepository
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.contract.MainContract
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_news.AddNewsState
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_news.NewsState
import timber.log.Timber

class NewsViewModel(
    private val subjectRepository: NewsRepository
) : ViewModel(), MainContract.ViewModelNews {

    private val subscriptions = CompositeDisposable()

    override val newsState: MutableLiveData<NewsState> = MutableLiveData()
    override val addDone: MutableLiveData<AddNewsState> = MutableLiveData()


    override fun fetchAllMovies() {
        val subscription = subjectRepository
            .fetchAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    newsState.value = NewsState.Success(it)
                   // Timber.e("OVO JE VIEW MODEL")
                   // Timber.e(it.toString())

                },
                {
                    newsState.value = NewsState.Error("Error happened while fetching data from the server")
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