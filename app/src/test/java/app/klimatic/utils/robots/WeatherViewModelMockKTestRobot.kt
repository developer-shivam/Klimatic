package app.klimatic.utils.robots

import androidx.lifecycle.Observer
import app.klimatic.data.remote.weather.WeatherResponse
import app.klimatic.data.response.Response
import app.klimatic.ui.utils.ViewState
import app.klimatic.ui.weather.domain.WeatherDataManager
import app.klimatic.ui.weather.presentation.WeatherViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK

class WeatherViewModelMockKTestRobot {

    lateinit var viewModel: WeatherViewModel

    @RelaxedMockK
    lateinit var dataManager: WeatherDataManager

    @RelaxedMockK
    lateinit var weatherObserver: Observer<ViewState<WeatherResponse>>

    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = WeatherViewModel(repository = dataManager).apply {
            weather.observeForever(weatherObserver)
        }
    }

    fun verifyWeatherObserver(value: ViewState<WeatherResponse>) {
        weatherObserver.onChanged(value)
    }

    fun coEveryFetchWeatherApiReturns(value: Response<WeatherResponse>) {
        coEvery { dataManager.fetchWeatherRemote(any()) } returns value
    }

    fun tearDown() {
        viewModel.weather.removeObserver(weatherObserver)
    }
}
