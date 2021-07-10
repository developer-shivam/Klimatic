package app.klimatic

import android.app.Application
import app.klimatic.di.modules.appModules
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
            modules(getAppModules())
        }
    }

    open fun getAppModules() = appModules
}
