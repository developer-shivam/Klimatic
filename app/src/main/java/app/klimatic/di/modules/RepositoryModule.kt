package app.klimatic.di.modules

import app.klimatic.ui.currentweather.domain.CurrentWeatherDataRepository
import app.klimatic.ui.currentweather.domain.CurrentWeatherDataRepositoryImpl
import app.klimatic.ui.currentweather.domain.CurrentWeatherUseCase
import app.klimatic.ui.currentweather.domain.CurrentWeatherUseCaseImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<CurrentWeatherDataRepository> { CurrentWeatherDataRepositoryImpl(get()) }
    factory<CurrentWeatherUseCase> { CurrentWeatherUseCaseImpl(get(), get()) }
}
