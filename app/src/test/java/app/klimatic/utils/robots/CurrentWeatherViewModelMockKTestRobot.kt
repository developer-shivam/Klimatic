package app.klimatic.utils.robots

import androidx.lifecycle.Observer
import app.klimatic.data.remote.weather.CurrentWeatherResponse
import app.klimatic.data.response.Response
import app.klimatic.ui.currentweather.domain.CurrentWeatherUseCase
import app.klimatic.ui.currentweather.presentation.CurrentWeatherViewModel
import app.klimatic.ui.utils.ViewState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK

class CurrentWeatherViewModelMockKTestRobot {

    lateinit var viewModel: CurrentWeatherViewModel

    @RelaxedMockK
    lateinit var useCase: CurrentWeatherUseCase

    @RelaxedMockK
    lateinit var weatherObserver: Observer<ViewState<CurrentWeatherResponse>>

    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CurrentWeatherViewModel(useCase = useCase).apply {
            weatherListener.observeForever(weatherObserver)
        }
    }

    fun verifyWeatherObserver(value: ViewState<CurrentWeatherResponse>) {
        weatherObserver.onChanged(value)
    }

    fun coEveryFetchCurrentWeatherApiReturns(value: Response<CurrentWeatherResponse>) {
        coEvery { useCase.fetchCurrentWeather(any()) } returns value
    }

    fun tearDown() {
        viewModel.weatherListener.removeObserver(weatherObserver)
    }
}
