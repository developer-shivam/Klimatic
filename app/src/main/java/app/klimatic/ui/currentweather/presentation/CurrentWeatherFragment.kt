package app.klimatic.ui.currentweather.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.klimatic.R
import app.klimatic.data.model.weather.Current.Companion.DAY
import app.klimatic.ui.base.BaseFragment
import app.klimatic.ui.currentweather.presentation.adapter.ForeCastAdapter
import app.klimatic.ui.utils.getCurrentHours
import app.klimatic.ui.utils.handleState
import app.klimatic.ui.utils.hide
import app.klimatic.ui.utils.show
import kotlinx.android.synthetic.main.fragment_weather.currentWeather
import kotlinx.android.synthetic.main.fragment_weather.currentWeatherConditionLottieView
import kotlinx.android.synthetic.main.fragment_weather.rvForeCast
import kotlinx.android.synthetic.main.fragment_weather.tvToday
import kotlinx.android.synthetic.main.fragment_weather.view.rvForeCast
import kotlinx.android.synthetic.main.fragment_weather.waveView
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrentWeatherFragment : BaseFragment() {

    private val currentWeatherViewModel by viewModel<CurrentWeatherViewModel>()

    private val foreCastAdapter by lazy {
        context?.let {
            ForeCastAdapter(it)
        }
    }

    private val query: String = "DELHI"

    companion object {
        fun create() = CurrentWeatherFragment()
    }

    override fun getLayoutResource(): Int = R.layout.fragment_weather

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        setupObservers()
        setUpForeCastView(view)
        waveView.setLifecycle(lifecycle)
        currentWeatherViewModel.fetchCurrentWeather(query)
        currentWeatherViewModel.fetchForeCast(query)
    }

    private fun setUpForeCastView(view: View) {
        view.rvForeCast.run {
            layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = foreCastAdapter
        }
    }

    private fun setupObservers() {
        currentWeatherViewModel.run {
            weatherListener.observe(this@CurrentWeatherFragment, Observer { state ->
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

            foreCastListener.observe(this@CurrentWeatherFragment, Observer { state ->
                handleState(state,
                    { data ->
                        foreCastAdapter?.updateForeCastData(
                            updatedList = data.forecast?.forecastDay?.get(
                                0
                            )?.hour
                        )
                        showForeCastView()
                    }, {
                        hideForeCastView()
                    })
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
