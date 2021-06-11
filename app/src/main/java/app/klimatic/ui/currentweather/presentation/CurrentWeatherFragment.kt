package app.klimatic.ui.currentweather.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import app.klimatic.R
import app.klimatic.di.components.ActivityComponent
import app.klimatic.ui.base.BaseFragment
import app.klimatic.ui.utils.ViewState
import kotlinx.android.synthetic.main.fragment_weather.*
import javax.inject.Inject

class CurrentWeatherFragment : BaseFragment() {

    @Inject
    lateinit var currentWeatherViewModel: CurrentWeatherViewModel

    companion object {

        fun create() : CurrentWeatherFragment {
            // No arguments as of now. So returning instance right away
            return CurrentWeatherFragment()
        }
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
            when (state) {
                is ViewState.Success -> {
                    state.data?.let {
                        currentWeather.setCurrentWeatherData(it)
                    }
                }
                is ViewState.Error -> {

                }
                is ViewState.StartLoading -> {

                }
                is ViewState.HideLoading -> {

                }
            }
        })
    }

}