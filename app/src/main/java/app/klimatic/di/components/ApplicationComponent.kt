package app.klimatic.di.components

import app.klimatic.data.remote.weather.CurrentWeatherService
import app.klimatic.di.modules.NetworkModule
import app.klimatic.di.scopes.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun provideCurrentWeatherService(): CurrentWeatherService
}