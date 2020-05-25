package ua.vlad.uklon.presentation

import android.app.Application
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ua.vlad.uklon.BuildConfig
import ua.vlad.uklon.data.platform.NetConnectionHandler
import ua.vlad.uklon.di.data.cacheModule
import ua.vlad.uklon.di.data.dataSourceModule
import ua.vlad.uklon.di.data.netModule
import ua.vlad.uklon.di.data.repositoryModule
import ua.vlad.uklon.di.domain.domainModule
import ua.vlad.uklon.di.presentation.viewModelModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initNetConnectionHandler()
        initDI()
    }

    private fun initNetConnectionHandler() {
        NetConnectionHandler.initialize(this)
    }

    private fun initDI() {
        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger(Level.DEBUG)
            modules(
                netModule,
                cacheModule,
                dataSourceModule,
                repositoryModule,
                domainModule,
                viewModelModule
            )
        }
    }

}