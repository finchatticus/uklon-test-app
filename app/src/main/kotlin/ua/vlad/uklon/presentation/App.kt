package ua.vlad.uklon.presentation

import android.app.Application
import org.koin.core.context.startKoin
import ua.vlad.uklon.di.presentation.viewModelModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        startKoin {
            modules(viewModelModule)
        }
    }

}