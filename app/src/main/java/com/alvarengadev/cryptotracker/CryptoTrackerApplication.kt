package com.alvarengadev.cryptotracker

import android.app.Application
import com.alvarengadev.cryptotracker.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CryptoTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptoTrackerApplication)
            modules(appModules)
        }
    }
}
