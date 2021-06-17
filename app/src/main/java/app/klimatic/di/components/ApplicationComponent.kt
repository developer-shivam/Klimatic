package app.klimatic.di.components

import app.klimatic.data.model.local.CurrentWeatherDao
import app.klimatic.data.remote.weather.CurrentWeatherService
import app.klimatic.di.modules.DatabaseModule
import app.klimatic.di.modules.NetworkModule
import app.klimatic.di.scopes.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface ApplicationComponent {

    fun provideCurrentWeatherService(): CurrentWeatherService

    fun provideCurrentWeatherDao(): CurrentWeatherDao
}
