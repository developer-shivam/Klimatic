package app.klimatic.ui.currentweather.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import app.klimatic.R
import app.klimatic.di.components.ActivityComponent
import app.klimatic.ui.base.BaseFragment
import app.klimatic.ui.utils.handleState
import app.klimatic.ui.utils.hide
import app.klimatic.ui.utils.show
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_weather.currentWeather

class CurrentWeatherFragment : BaseFragment() {

    @Inject
    lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    companion object {
        fun create() = CurrentWeatherFragment()
    }

    override fun getLayoutResource(): Int = R.layout.fragment_weather

    override fun performDependencyInjection(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        setupObservers()
        currentWeatherViewModel.fetchCurrentWeather("Delhi")
    }

    private fun setupObservers() {
        currentWeatherViewModel.weatherListener.observe(this, Observer { state ->
            handleState(state,
                { data ->
                    if (data.location != null && data.current != null) {
                        currentWeather.show()
                        currentWeather.setCurrentWeatherData(data)
                    }
                }, {
                    currentWeather.hide()
                })
        })
    }
}
