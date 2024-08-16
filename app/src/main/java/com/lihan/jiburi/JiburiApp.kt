package com.lihan.jiburi

import android.app.Application
import com.lihan.jiburi.core.di.coreDataModule
import com.lihan.jiburi.di.filmsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class JiburiApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@JiburiApp)
            modules(
                coreDataModule,
                filmsModule
            )
        }
    }
}