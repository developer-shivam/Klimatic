package app.klimatic.di.modules

import app.klimatic.di.scopes.ActivityScope
import app.klimatic.ui.weather.domain.WeatherDataRepository
import app.klimatic.ui.weather.domain.WeatherDataRepositoryImpl
import app.klimatic.ui.weather.presentation.WeatherViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @ActivityScope
    @Provides
    fun provideWeatherRepository(repository: WeatherDataRepositoryImpl): WeatherDataRepository {
        return repository
    }

    @ActivityScope
    @Provides
    fun provideWeatherViewModel(repository: WeatherDataRepository): WeatherViewModel {
        return WeatherViewModel(repository)
    }
}
