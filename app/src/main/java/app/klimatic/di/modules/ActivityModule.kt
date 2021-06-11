package app.klimatic.di.modules

import app.klimatic.di.scopes.ActivityScope
import app.klimatic.ui.currentweather.domain.CurrentWeatherDataRepository
import app.klimatic.ui.currentweather.domain.CurrentWeatherDataRepositoryImpl
import app.klimatic.ui.currentweather.presentation.CurrentWeatherViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @ActivityScope
    @Provides
    fun provideWeatherRepository(repository: CurrentWeatherDataRepositoryImpl)
            : CurrentWeatherDataRepository {
        return repository
    }

    @ActivityScope
    @Provides
    fun provideWeatherViewModel(repository: CurrentWeatherDataRepository)
            : CurrentWeatherViewModel {
        return CurrentWeatherViewModel(repository)
    }
}
