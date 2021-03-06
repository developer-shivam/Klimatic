package app.klimatic.di.modules

import app.klimatic.ui.search.presentation.viewmodel.SearchViewModel
import app.klimatic.ui.weather.presentation.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { WeatherViewModel(get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
}
