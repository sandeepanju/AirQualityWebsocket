package com.example.airqualitywebsocket.core

import android.app.Application
import com.example.airqualitywebsocket.di.netModule
import com.example.airqualitywebsocket.di.repositoryModule
import com.example.airqualitywebsocket.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppClass : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppClass)
            androidLogger(Level.DEBUG)
            modules(listOf(viewModelModule, netModule, repositoryModule))
        }
    }
}