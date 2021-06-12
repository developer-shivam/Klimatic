package app.klimatic

import android.app.Application
import app.klimatic.di.components.ApplicationComponent
import app.klimatic.di.components.DaggerApplicationComponent

open class KlimaticApplication : Application() {

    var _applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        _applicationComponent = DaggerApplicationComponent.create()
    }

    open fun getApplicationComponent(): ApplicationComponent? {
        return _applicationComponent
    }
}
