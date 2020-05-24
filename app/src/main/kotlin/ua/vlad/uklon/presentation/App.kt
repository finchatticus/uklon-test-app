package ua.vlad.uklon.presentation

import android.app.Application
import org.koin.core.context.startKoin
import ua.vlad.uklon.data.platform.NetConnectionHandler
import ua.vlad.uklon.di.data.*
import ua.vlad.uklon.di.domain.*
import ua.vlad.uklon.di.presentation.*

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
            modules(
                netModule,
                dataSourceModule,
                repositoryModule,
                domainModule,
                viewModelModule
            )
        }
    }

}