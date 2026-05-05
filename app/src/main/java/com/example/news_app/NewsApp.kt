package com.example.news_app

import android.app.Application
import com.example.news_app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsApp:Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsApp)
            androidLogger()

            modules(appModule)
        }
    }
}