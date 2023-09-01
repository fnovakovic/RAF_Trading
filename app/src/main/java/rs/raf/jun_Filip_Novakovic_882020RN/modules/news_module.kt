package rs.raf.jun_Filip_Novakovic_882020RN.modules

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import rs.raf.jun_Filip_Novakovic_882020RN.data.repositories.*
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.viewmodel.NewsViewModel
import rs.raf.vezbe8.data.datasources.rx.RxJsonNews
import rs.raf.vezbe8.data.datasources.rx.RxNews

val news_module = module {


    viewModel { NewsViewModel( get()) }

    single<NewsRepository> { NewsRepositoryImpl(get(named("json"))) }



    single<RxNews>(named("json")) { RxJsonNews(context = androidApplication(), moshi = get()) }
}