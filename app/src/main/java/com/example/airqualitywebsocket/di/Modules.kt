package com.example.airqualitywebsocket.di

import com.example.airqualitywebsocket.BuildConfig
import com.example.airqualitywebsocket.repository.MainRepository
import com.example.airqualitywebsocket.viewModel.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit


val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val netModule = module {

    fun provideHttpClient(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder().readTimeout(3, TimeUnit.SECONDS)
        return httpBuilder.build()
    }

    fun provideRequest() = Request.Builder()
        .url(BuildConfig.WEB_SOCKET_ENDPOINT)
        .build()


    single { provideHttpClient() }
    single { provideRequest() }
}

val repositoryModule = module {
    fun provideUserRepository(request: Request, client: OkHttpClient) =
        MainRepository(request, client)

    single { provideUserRepository(get(), get()) }
}