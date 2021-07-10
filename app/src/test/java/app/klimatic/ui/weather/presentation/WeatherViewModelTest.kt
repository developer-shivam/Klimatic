package app.klimatic.ui.weather.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import app.klimatic.data.remote.weather.WeatherResponse
import app.klimatic.data.response.Response
import app.klimatic.ui.weather.domain.WeatherDataManager
import app.klimatic.ui.utils.ViewState
import app.klimatic.utils.TestCoroutineRule
import app.klimatic.utils.factory.TestFactory.EMPTY_QUERY
import app.klimatic.utils.factory.TestFactory.MOCKED_ERROR_CODE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var dataManager: WeatherDataManager

    @Mock
    private lateinit var weatherObserver: Observer<ViewState<WeatherResponse>>

    @Mock
    private lateinit var weatherResponse: WeatherResponse

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    private lateinit var viewModel: WeatherViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = WeatherViewModel(dataManager).apply {
            weather.observeForever(weatherObserver)
            ioScope = testCoroutineScope
        }
    }

    @Test
    fun givenSuccessResponse_whenFetchCurrentWeather_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {

            // Given
            whenever(dataManager.fetchWeatherRemote(EMPTY_QUERY))
                .thenReturn(Response.Success(weatherResponse))

            // When
            viewModel.fetchWeatherRemote(EMPTY_QUERY)

            // Then
            verify(weatherObserver).onChanged(ViewState.Success(weatherResponse))
        }
    }

    @Test
    fun givenErrorResponse_whenFetchCurrentWeather_shouldReturnError() {
        testCoroutineRule.runBlockingTest {

            // Given
            whenever(dataManager.fetchWeatherRemote(EMPTY_QUERY))
                .thenReturn(Response.Error(MOCKED_ERROR_CODE))

            // When
            viewModel.fetchWeatherRemote(EMPTY_QUERY)

            // Then
            verify(weatherObserver).onChanged(ViewState.Error(MOCKED_ERROR_CODE))
        }
    }

    @After
    fun clear() {
        viewModel.weather.removeObserver(weatherObserver)
    }
}
