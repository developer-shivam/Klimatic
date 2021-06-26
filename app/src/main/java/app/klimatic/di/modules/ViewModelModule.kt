package app.klimatic.di.modules

import app.klimatic.ui.currentweather.presentation.CurrentWeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CurrentWeatherViewModel(get()) }
}
