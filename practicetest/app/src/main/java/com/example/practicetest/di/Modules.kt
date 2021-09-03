package com.example.practicetest.di

import android.app.Application
import androidx.room.Room
import com.example.demo.retrofit.RetrofitClient
import com.example.practicetest.database.Dao
import com.example.practicetest.database.ToDoDatabase
import com.example.practicetest.repository.ToDoRepository
import com.example.practicetest.retrofit.ApiInterface
import com.example.practicetest.viewmodel.ToDoViewModel
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
    fun getDatabaseClient(application: Application): ToDoDatabase {
        return Room.databaseBuilder(
            application,
            ToDoDatabase::class.java,
            "TODO_DATABASE"
        ).build()

    }

    fun provideDao(database: ToDoDatabase): Dao {
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
    fun musicRepository(dao:Dao,retrofitClient: RetrofitClient): ToDoRepository {
        return ToDoRepository(dao,retrofitClient)
    }
    single { musicRepository(get(),get()) }

}

val viewModel = module {
    viewModel { ToDoViewModel(get()) }

}


