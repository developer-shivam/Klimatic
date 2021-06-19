package app.klimatic.di.modules

import app.klimatic.data.model.local.CurrentWeatherDao
import app.klimatic.data.remote.service.WeatherService
import app.klimatic.ui.currentweather.domain.CurrentWeatherDataRepository
import app.klimatic.ui.currentweather.domain.CurrentWeatherDataRepositoryImpl
import app.klimatic.ui.currentweather.domain.CurrentWeatherUseCase
import app.klimatic.ui.currentweather.domain.CurrentWeatherUseCaseImpl
import app.klimatic.ui.currentweather.presentation.CurrentWeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    fun provideWeatherRepository(
        currentWeatherService: WeatherService
    ): CurrentWeatherDataRepository {
        return CurrentWeatherDataRepositoryImpl(currentWeatherService)
    }

    fun provideWeatherUseCase(
        currentWeatherDataRepository: CurrentWeatherDataRepository,
        currentWeatherDao: CurrentWeatherDao
    ): CurrentWeatherUseCase =
        CurrentWeatherUseCaseImpl(currentWeatherDataRepository, currentWeatherDao)

    single {
        provideWeatherRepository(get())
    }

    single {
        provideWeatherUseCase(get(), get())
    }

    viewModel {
        CurrentWeatherViewModel(get())
    }
}
