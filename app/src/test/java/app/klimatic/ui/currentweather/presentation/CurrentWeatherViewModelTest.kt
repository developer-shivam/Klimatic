package app.klimatic.ui.currentweather.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import app.klimatic.data.remote.weather.CurrentWeatherResponse
import app.klimatic.data.response.Response
import app.klimatic.ui.currentweather.domain.CurrentWeatherDataRepository
import app.klimatic.ui.utils.ViewState
import app.klimatic.utils.TestCoroutineRule
import app.klimatic.utils.TestFactory.MOCKED_ERROR_CODE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CurrentWeatherViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var currentWeatherDataRepository: CurrentWeatherDataRepository

    @Mock
    private lateinit var weatherObserver: Observer<ViewState<CurrentWeatherResponse>>

    @Mock
    private lateinit var currentWeatherResponse: CurrentWeatherResponse

    private lateinit var viewModel: CurrentWeatherViewModel

    @Before
    fun setup() {
        viewModel = CurrentWeatherViewModel(currentWeatherDataRepository).apply {
            weatherListener.observeForever(weatherObserver)
        }
    }

    @Test
    fun givenSuccessResponse_whenFetchCurrentWeather_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {

            doReturn(Response.Success(currentWeatherResponse))
                .`when`(currentWeatherDataRepository)
                .fetchCurrentWeather(anyString())

            viewModel.fetchCurrentWeather(anyString())

            verify(weatherObserver).onChanged(ViewState.Success(currentWeatherResponse))
        }
    }

    @Test
    fun givenErrorResponse_whenFetchCurrentWeather_shouldReturnError() {
        testCoroutineRule.runBlockingTest {

            doReturn(Response.Error<CurrentWeatherResponse>(MOCKED_ERROR_CODE))
                .`when`(currentWeatherDataRepository)
                .fetchCurrentWeather(anyString())

            viewModel.fetchCurrentWeather(anyString())

            verify(weatherObserver).onChanged(ViewState.Error(MOCKED_ERROR_CODE))
        }
    }

    @After
    fun clear() {
        viewModel.weatherListener.removeObserver(weatherObserver)
    }
}
