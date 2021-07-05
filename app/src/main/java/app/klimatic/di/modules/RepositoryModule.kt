package app.klimatic.di.modules

import app.klimatic.ui.weather.domain.WeatherDataManager
import app.klimatic.ui.weather.domain.WeatherDataManagerImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<WeatherDataManager> { WeatherDataManagerImpl(get(), get()) }
}
