package app.klimatic.di.module

import app.klimatic.di.modules.NetworkModule
import app.klimatic.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class TestNetworkModule : NetworkModule() {

    @ApplicationScope
    @Provides
    override fun provideBaseUrl(): String = "http://localhost:8080"
}
