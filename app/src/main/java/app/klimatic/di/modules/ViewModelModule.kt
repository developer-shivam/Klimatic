package app.klimatic.di.modules

import app.klimatic.data.model.local.WeatherDao
import app.klimatic.data.remote.service.WeatherService
import app.klimatic.ui.weather.domain.WeatherDataManager
import app.klimatic.ui.weather.domain.WeatherDataManagerImpl
import app.klimatic.ui.weather.presentation.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    fun provideWeatherRepository(
        weatherDao: WeatherDao,
        weatherService: WeatherService
    ): WeatherDataManager {
        return WeatherDataManagerImpl(weatherDao, weatherService)
    }

    single {
        provideWeatherRepository(get(), get())
    }

    viewModel {
        WeatherViewModel(get())
    }
}
