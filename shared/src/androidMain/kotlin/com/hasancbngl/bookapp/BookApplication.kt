package com.hasancbngl.bookapp

import android.app.Application
import com.hasancbngl.bookapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@BookApplication)
        }
    }
}