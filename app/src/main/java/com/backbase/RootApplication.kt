package com.backbase

import android.app.Application
import com.backbase.data.di.dataModule
import com.backbase.domain.di.domainModule
import com.backbase.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class RootApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@RootApplication)
            modules(
                uiModule,
                domainModule,
                dataModule
            )
        }
    }
}
