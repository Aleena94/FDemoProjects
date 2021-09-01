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
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val netmodule = module {

    single { retrofitInstance() }

}

fun retrofitInstance(): RetrofitClient {
    return RetrofitClient
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

    fun movieRepository(retrofitClient: RetrofitClient): MovieListRepository {
        return MovieListRepository(retrofitClient)
    }

    fun musicRepository(retrofitClient: RetrofitClient): MusicListRepository {
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


