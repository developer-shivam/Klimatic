package app.klimatic

import android.app.Application
import app.klimatic.di.components.ApplicationComponent
import app.klimatic.di.components.DaggerApplicationComponent
import app.klimatic.di.modules.DatabaseModule
import app.klimatic.di.modules.NetworkModule

open class KlimaticApplication : Application() {

    var _applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        _applicationComponent = DaggerApplicationComponent.builder()
            .networkModule(NetworkModule())
            .databaseModule(DatabaseModule(applicationContext))
            .build()
    }

    open fun getApplicationComponent(): ApplicationComponent? {
        return _applicationComponent
    }
}
