package com.bagaspardanailham.greenheroesapp

import android.app.Application
import com.bagaspardanailham.greenheroesapp.provideKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApplication)
        }
    }

    fun initKoin(config: KoinAppDeclaration? = null) {
        startKoin {
            config?.invoke(this)
//            androidContext(this@MyApplication)
            provideKoinModule()
        }
    }
}