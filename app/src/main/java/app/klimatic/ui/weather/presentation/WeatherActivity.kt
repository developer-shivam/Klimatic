package app.klimatic.ui.weather.presentation

import android.os.Bundle
import app.klimatic.R
import app.klimatic.data.remote.CurrentWeatherService
import app.klimatic.di.components.ActivityComponent
import app.klimatic.ui.base.BaseActivity
import java.util.*
import javax.inject.Inject
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.klimatic.ui.utils.ErrorUtils
import app.klimatic.ui.utils.ViewState
import app.klimatic.ui.utils.toast
import app.klimatic.ui.weather.domain.WeatherDataRepository
import app.klimatic.ui.weather.domain.WeatherDataRepositoryImpl

class WeatherActivity : BaseActivity() {

    @Inject
    lateinit var currentWeatherService: CurrentWeatherService

    override fun getLayoutResource(): Int = R.layout.activity_weather

    override fun performDependencyInjection(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    //inject viewmodel
    lateinit var weatherViewModel: WeatherViewModel

    override fun setupView(savedInstanceState: Bundle?) {
        observe()
        weatherViewModel.fetchCurrentWeather("Delhi")
    }

    private fun observe() {
        weatherViewModel.weatherListener.observe(this, Observer {
            when(it) {
                is ViewState.Success -> { toast(it.data.toString())}
                is ViewState.Error -> { toast(ErrorUtils.resolveErrorCode(it.code))}
            }
        })
    }


}