package rs.raf.vezbe8.data.datasources.rx

import io.reactivex.Observable
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.StocksResponse

interface RxStock {
    fun getUserData(): Observable<StocksResponse>
}