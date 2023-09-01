package rs.raf.jun_Filip_Novakovic_882020RN.modules

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import rs.raf.jun_Filip_Novakovic_882020RN.data.repositories.*
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel.StocksViewModel
import rs.raf.vezbe8.data.datasources.rx.*

val stocks_module = module {


    viewModel { StocksViewModel( get()) }

    single<StockRepository> { StocksRepositoryImpl(get(named("json")),get(named("json2"))) }




    single<RxStock>(named("json")) { RxJsonStocks(context = androidApplication(), moshi = get()) }

    single<RxSingleStock>(named("json2")) { RxJsonSingleStock(context = androidApplication(), moshi = get()) }

    //single { get<StocksDataBase>().getStockstDao() }



}