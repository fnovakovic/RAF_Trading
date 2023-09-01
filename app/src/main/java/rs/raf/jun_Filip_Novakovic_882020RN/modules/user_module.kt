package rs.raf.jun_Filip_Novakovic_882020RN.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.local_stocks.StocksDataBase
import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.local_user.UserDataBase
import rs.raf.jun_Filip_Novakovic_882020RN.data.repositories.*
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel.ViewModel

val user_module = module {


    viewModel { ViewModel( get(),get()) }

    single<UserRepository> { UserRepositoryImpl(localDataSource = get()) }
   // single<StockRepository> { StocksRepositoryImpl(get(),get()) }

    single<StockRepositoryFetchFromBase> { StockRepositoryFetchFromBaseImpl(get()) }

   /* single<StocksService> { create(retrofit = get()) }*/
   // single<RxUserDataSource2>(named("json")) { RxJsonUserDataSource2(context = androidApplication(), moshi = get()) }
    single { get<UserDataBase>().getUserDao() }
    //single { get<StocksDataBase>().getStockstDao() }


    single { get<StocksDataBase>().getStockstDao() }


}