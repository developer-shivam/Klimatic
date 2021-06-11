package app.klimatic

import android.app.Application
import app.klimatic.di.components.ApplicationComponent
import app.klimatic.di.components.DaggerApplicationComponent

class KlimaticApplication : Application() {

    private var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.create()
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return applicationComponent
    }
}
