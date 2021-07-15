package app.klimatic.ui.weather.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.klimatic.R
import app.klimatic.data.model.weather.Current.Companion.DAY
import app.klimatic.ui.base.BaseFragment
import app.klimatic.ui.utils.getCurrentHours
import app.klimatic.ui.utils.handleState
import app.klimatic.ui.utils.hide
import app.klimatic.ui.utils.show
import app.klimatic.ui.weather.presentation.adapter.ForecastAdapter
import kotlinx.android.synthetic.main.fragment_weather.currentWeather
import kotlinx.android.synthetic.main.fragment_weather.currentWeatherConditionLottieView
import kotlinx.android.synthetic.main.fragment_weather.errorView
import kotlinx.android.synthetic.main.fragment_weather.rvForeCast
import kotlinx.android.synthetic.main.fragment_weather.swipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_weather.tvToday
import kotlinx.android.synthetic.main.fragment_weather.view.rvForeCast
import kotlinx.android.synthetic.main.fragment_weather.waveView
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : BaseFragment() {

    private val weatherViewModel by viewModel<WeatherViewModel>()

    private val foreCastAdapter by lazy {
        context?.let {
            ForecastAdapter(it)
        }
    }

    override fun getLayoutResource(): Int = R.layout.fragment_weather

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // On pressing back, if no location is selected, app will be closed.
        // If user selects a location, HomeActivity is relaunched.
        if (weatherViewModel.getCurrentSelectedLocation() == null) {
            openLocationChooserClosingWeatherFragment()
        } else {
            fetchWeather()
        }
    }

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        setupObservers()
        setUpForeCastView(view)

        waveView.setLifecycle(lifecycle)

        currentWeather.setOnCurrentLocationClickAction {
            openLocationChooser()
        }

        swipeRefreshLayout.setOnRefreshListener {
            fetchWeather()
        }
    }

    private fun openLocationChooser() {
        findNavController().navigate(R.id.action_weather_to_locationChooser)
    }

    private fun openLocationChooserClosingWeatherFragment() {
        findNavController().navigate(R.id.action_weather_to_locationChooser_closing_weather)
    }

    private fun fetchWeather() {
        weatherViewModel.fetchWeatherLocal()
        weatherViewModel.fetchWeatherRemote()
    }

    private fun setUpForeCastView(view: View) {
        view.rvForeCast.run {
            layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = foreCastAdapter
        }
    }

    private fun setupObservers() {
        weatherViewModel.run {
            weather.observe(this@WeatherFragment, Observer { state ->
                handleState(state,
                    { data ->
                        swipeRefreshLayout.isRefreshing = false
                        if (data.location != null && data.current != null) {
                            errorView.hide()
                            currentWeather.show()
                            currentWeather.setCurrentWeatherData(data)

                            data.current.condition?.let {
                                currentWeatherConditionLottieView.show()
                                currentWeatherConditionLottieView.setCurrentWeatherCondition(
                                    data.current.isDay == DAY,
                                    data.current.condition
                                )
                            }

                            foreCastAdapter?.updateForeCastData(
                                updatedList = data.forecast?.forecastDay?.get(0)?.hour
                            )
                            showForeCastView()
                        } else {
                            errorView.show()
                        }
                    }, {
                        swipeRefreshLayout.isRefreshing = false
                        currentWeather.hide()
                        currentWeatherConditionLottieView.hide()
                        hideForeCastView()
                        errorView.show()
                    }, {
                        swipeRefreshLayout.isRefreshing = true
                    }, {})
            })
        }
    }

    private fun showForeCastView() {
        tvToday.show()
        rvForeCast.show()
        rvForeCast.scrollToPosition(context.getCurrentHours())
    }

    private fun hideForeCastView() {
        tvToday.hide()
        rvForeCast.hide()
    }
}
