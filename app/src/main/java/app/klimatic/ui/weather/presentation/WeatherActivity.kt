package app.klimatic.ui.weather.presentation

import android.os.Bundle
import androidx.lifecycle.Observer
import app.klimatic.R
import app.klimatic.di.components.ActivityComponent
import app.klimatic.ui.base.BaseActivity
import app.klimatic.ui.utils.ErrorUtils
import app.klimatic.ui.utils.ViewState
import app.klimatic.ui.utils.toast
import javax.inject.Inject

class WeatherActivity : BaseActivity() {

    override fun getLayoutResource(): Int = R.layout.activity_weather

    override fun performDependencyInjection(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    @Inject
    lateinit var weatherViewModel: WeatherViewModel

    override fun setupView(savedInstanceState: Bundle?) {
        observe()
        weatherViewModel.fetchCurrentWeather("Delhi")
    }

    private fun observe() {
        weatherViewModel.weatherListener.observe(this, Observer {
            when (it) {
                is ViewState.Success -> { toast(it.data.toString()) }
                is ViewState.Error -> { toast(ErrorUtils.resolveErrorCode(it.code)) }
            }
        })
    }
}
