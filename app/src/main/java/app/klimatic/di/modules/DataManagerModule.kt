package app.klimatic.di.modules

import app.klimatic.ui.search.domain.SearchDataManager
import app.klimatic.ui.search.domain.SearchDataManagerImpl
import app.klimatic.ui.weather.domain.WeatherDataManager
import app.klimatic.ui.weather.domain.WeatherDataManagerImpl
import org.koin.dsl.module

val dataManagerModule = module {
    factory<WeatherDataManager> { WeatherDataManagerImpl(get(), get()) }
    factory<SearchDataManager> { SearchDataManagerImpl(get()) }
}
