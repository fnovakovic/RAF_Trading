package rs.raf.jun_Filip_Novakovic_882020RN.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import rs.raf.jun_Filip_Novakovic_882020RN.BuildConfig
import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.local_user.UserDataBase
import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.local_stocks.StocksDataBase
import java.util.concurrent.TimeUnit

val coreModule = module {

    single<SharedPreferences> {
        androidApplication().getSharedPreferences(androidApplication().packageName, Context.MODE_PRIVATE)
    }

    single { Room.databaseBuilder(androidContext(), UserDataBase::class.java, "UserDb")
        .fallbackToDestructiveMigration()
        .build() }

    single { Room.databaseBuilder(androidContext(), StocksDataBase::class.java, "StocksDb")
        .fallbackToDestructiveMigration()
        .build() }


    single<Moshi> { Moshi.Builder().build() }
    /*single { createRetrofit(moshi = get(), httpClient = get()) }*/

 /*   single { createMoshi() }*/

    single { createOkHttpClient() }
}
/*
fun createMoshi(): Moshi {
    return Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .build()
}*/

/*fun createRetrofit(moshi: Moshi,
                   httpClient: OkHttpClient
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://rfidis.raf.edu.rs/raspored/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .client(httpClient)
        .build()
}*/

fun createOkHttpClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.readTimeout(60, TimeUnit.SECONDS)
    httpClient.connectTimeout(60, TimeUnit.SECONDS)
    httpClient.writeTimeout(60, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
    }

    return httpClient.build()
}

// Metoda koja kreira servis
inline fun <reified T> create(retrofit: Retrofit): T  {
    return retrofit.create(T::class.java)
}

