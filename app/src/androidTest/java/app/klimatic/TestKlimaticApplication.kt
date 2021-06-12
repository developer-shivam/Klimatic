package app.klimatic

import app.klimatic.di.components.ApplicationComponent
import app.klimatic.di.components.DaggerApplicationComponent
import app.klimatic.di.module.TestNetworkModule

class TestKlimaticApplication : KlimaticApplication() {

    override fun getApplicationComponent(): ApplicationComponent? {
        return DaggerApplicationComponent.builder()
            .networkModule(TestNetworkModule())
            .build()
    }
}
