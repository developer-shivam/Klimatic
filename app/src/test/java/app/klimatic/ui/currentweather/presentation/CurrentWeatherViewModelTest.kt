package app.klimatic.ui.currentweather.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import app.klimatic.data.remote.weather.CurrentWeatherResponse
import app.klimatic.data.response.Response
import app.klimatic.ui.currentweather.domain.CurrentWeatherDataRepository
import app.klimatic.ui.utils.ViewState
import app.klimatic.utils.TestCoroutinesRule
import app.klimatic.utils.TestFactory
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CurrentWeatherViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutinesRule = TestCoroutinesRule()

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    lateinit var viewModel: CurrentWeatherViewModel

    @RelaxedMockK
    lateinit var repository: CurrentWeatherDataRepository

    @RelaxedMockK
    lateinit var weatherObserver: Observer<ViewState<CurrentWeatherResponse>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = CurrentWeatherViewModel(repository = repository).apply {
            weatherListener.observeForever(weatherObserver)
        }
    }

    /************ Tests for fetch current weather ************/
    /************ Start ************/
    @Test
    fun `GIVEN query WHEN fetch current weather api RETURNS current weather response WHILE fetching current weather THEN post success`() {
        val weatherSlot = slot<ViewState.Success<CurrentWeatherResponse>>()
        val query = "xx"
        val expectValue = TestFactory.currentWeatherResponse
        coEveryFetchCurrentWeatherApiReturns(value = Response.Success(data = expectValue))
        viewModel.fetchCurrentWeather(query = query)
        verify {
            verifyWeatherObserver(value = capture(weatherSlot))
        }
        val actualValue = weatherSlot.captured
        assertThat(actualValue.data.current).isEqualTo(expectValue.current)
        assertThat(actualValue.data.location).isEqualTo(expectValue.location)
    }

    @Test
    fun `WHEN fetch current weather api RETURNS current weather response WHILE fetching current weather THEN post success`() {
        val weatherSlot = slot<ViewState.Success<CurrentWeatherResponse>>()
        val expectValue = TestFactory.currentWeatherResponse
        coEveryFetchCurrentWeatherApiReturns(value = Response.Success(data = expectValue))
        viewModel.fetchCurrentWeather()
        verify {
            verifyWeatherObserver(value = capture(weatherSlot))
        }
        val actualValue = weatherSlot.captured
        assertThat(actualValue.data.current).isEqualTo(expectValue.current)
        assertThat(actualValue.data.location).isEqualTo(expectValue.location)
    }

    @Test
    fun `GIVEN query WHEN fetch current weather api RETURNS error WHILE fetching current weather THEN show error`() {
        val query = "xx"
        coEveryFetchCurrentWeatherApiReturns(value = Response.Error(TestFactory.ERROR_CODE_GENERIC))
        viewModel.fetchCurrentWeather(query = query)
        verify {
            verifyWeatherObserver(value = ViewState.Error(TestFactory.ERROR_CODE_GENERIC))
        }
    }

    @Test
    fun `WHEN fetch current weather api RETURNS error WHILE fetching current weather THEN show error`() {
        coEveryFetchCurrentWeatherApiReturns(value = Response.Error(TestFactory.ERROR_CODE_GENERIC))
        viewModel.fetchCurrentWeather()
        verify {
            verifyWeatherObserver(value = ViewState.Error(TestFactory.ERROR_CODE_GENERIC))
        }
    }

    /************ END ************/

    private fun verifyWeatherObserver(value: ViewState<CurrentWeatherResponse>) {
        weatherObserver.onChanged(value)
    }

    private fun coEveryFetchCurrentWeatherApiReturns(value: Response<CurrentWeatherResponse>) {
        coEvery { repository.fetchCurrentWeather(any()) } returns value
    }

    @After
    fun tearDown() {
        viewModel.weatherListener.removeObserver(weatherObserver)
    }
}
