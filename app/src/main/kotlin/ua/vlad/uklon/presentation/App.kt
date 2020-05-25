package ua.vlad.uklon.presentation

import android.app.Application
import org.koin.core.context.startKoin
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