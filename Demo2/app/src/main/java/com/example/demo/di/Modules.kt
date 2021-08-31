package com.example.demo.di

import android.app.Application
import androidx.room.Room
import com.example.demo.database.Dao
import com.example.demo.database.LoginDatabase
import com.example.demo.repository.LoginRepository
import com.example.demo.repository.MovieListRepository
import com.example.demo.repository.MusicListRepository
import com.example.demo.retrofit.ApiInterface
import com.example.demo.retrofit.RetrofitClient
import com.example.demo.viewmodel.LoginViewModel
import com.example.demo.viewmodel.MovieViewModel
import com.example.demo.viewmodel.MusicListViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



const val MUSIC: String = "https://ws.audioscrobbler.com/"
const val MOVIE: String = "https://api.themoviedb.org/"


val netmodule = module {

   /* factory { getRetrofitInstance(get()) }
    factory { getRetrofitInstance2(get()) }
    single { provideOkHttpClient(get()) }
    single { provideCache(androidApplication()) }*/
    single { retrofitInstance() }

}
fun retrofitInstance(): RetrofitClient{
    return RetrofitClient
}
fun getRetrofitInstance(client: OkHttpClient): Retrofit? = Retrofit.Builder()
    .baseUrl(MUSIC)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

fun getRetrofitInstance2(client: OkHttpClient): Retrofit? = Retrofit.Builder()
    .baseUrl(MOVIE)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

fun provideOkHttpClient(
    cache: Cache
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .cache(cache)
        .build()
}

fun provideCache(application: Application): Cache {
    val cacheSize = 10 * 1024 * 1024
    return Cache(application.cacheDir, cacheSize.toLong())
}

val database = module {
    fun getDatabaseClient(application: Application): LoginDatabase {
        return Room.databaseBuilder(
            application,
            LoginDatabase::class.java,
            "LOGIN_DATABASE"
        ).build()

    }
    fun provideDao(database: LoginDatabase): Dao {
        return database.dbOperationsDao
    }
    single { getDatabaseClient(androidApplication()) }
    single { provideDao(get()) }
}

val apiInterface = module {
    fun provideUserApi(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
    single {
        provideUserApi(get())
    }
}

val repository = module {
    fun loginRepository(dao: Dao): LoginRepository {
        return LoginRepository(dao)
    }
    fun movieRepository(retrofitClient:RetrofitClient): MovieListRepository {
        return MovieListRepository(retrofitClient)
    }
    fun musicRepository(retrofitClient:RetrofitClient): MusicListRepository {
        return MusicListRepository(retrofitClient)
    }
    single { loginRepository(get()) }
    single { movieRepository(get()) }
    single { musicRepository(get()) }
}

val viewModel = module {
    viewModel { LoginViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { MusicListViewModel(get()) }
}


