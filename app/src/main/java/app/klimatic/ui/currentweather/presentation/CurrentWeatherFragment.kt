package app.klimatic.ui.currentweather.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import app.klimatic.R
import app.klimatic.data.model.weather.Current.Companion.DAY
import app.klimatic.ui.base.BaseFragment
import app.klimatic.ui.utils.handleState
import app.klimatic.ui.utils.hide
import app.klimatic.ui.utils.show
import kotlinx.android.synthetic.main.fragment_weather.currentWeather
import kotlinx.android.synthetic.main.fragment_weather.currentWeatherConditionLottieView
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentWeatherFragment : BaseFragment() {

    private val currentWeatherViewModel by viewModel<CurrentWeatherViewModel>()

    companion object {
        fun create() = CurrentWeatherFragment()
    }

    override fun getLayoutResource(): Int = R.layout.fragment_weather

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        setupObservers()
        currentWeatherViewModel.fetchCurrentWeather()
    }

    private fun setupObservers() {
        currentWeatherViewModel.weatherListener.observe(this, Observer { state ->
            handleState(state,
                { data ->
                    if (data.location != null && data.current != null) {
                        currentWeather.show()
                        currentWeather.setCurrentWeatherData(data)

                        data.current.condition?.let {
                            currentWeatherConditionLottieView.show()
                            currentWeatherConditionLottieView.setCurrentWeatherCondition(
                                data.current.isDay == DAY,
                                data.current.condition
                            )
                        }
                    }
                }, {
                    currentWeather.hide()
                    currentWeatherConditionLottieView.hide()
                })
        })
    }
}
