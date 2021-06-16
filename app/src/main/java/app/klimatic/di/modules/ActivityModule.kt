package app.klimatic.di.modules

import app.klimatic.di.scopes.ActivityScope
import app.klimatic.ui.currentweather.domain.CurrentWeatherDataRepository
import app.klimatic.ui.currentweather.domain.CurrentWeatherDataRepositoryImpl
import app.klimatic.ui.currentweather.domain.CurrentWeatherUseCase
import app.klimatic.ui.currentweather.domain.CurrentWeatherUseCaseImpl
import app.klimatic.ui.currentweather.presentation.CurrentWeatherViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @ActivityScope
    @Provides
    fun provideWeatherRepository(repository: CurrentWeatherDataRepositoryImpl):
            CurrentWeatherDataRepository {
        return repository
    }

    @ActivityScope
    @Provides
    fun provideWeatherUseCase(repository: CurrentWeatherUseCaseImpl):
            CurrentWeatherUseCase {
        return repository
    }

    @ActivityScope
    @Provides
    fun provideWeatherViewModel(useCase: CurrentWeatherUseCase):
            CurrentWeatherViewModel {
        return CurrentWeatherViewModel(useCase)
    }
}
