package app.klimatic

import android.app.Application
import app.klimatic.di.modules.databaseModule
import app.klimatic.di.modules.networkModule
import app.klimatic.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class KlimaticApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@KlimaticApplication)
            modules(
                listOf(
                    getNetworkModule(), databaseModule, viewModelModule
                )
            )
        }
    }

    open fun getNetworkModule() = networkModule
}
