package app.klimatic.di.modules

import app.klimatic.ui.locationchooser.presentation.LocationViewModel
import app.klimatic.ui.search.presentation.viewmodel.SearchViewModel
import app.klimatic.ui.weather.presentation.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LocationViewModel(get()) }
    viewModel { WeatherViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}
