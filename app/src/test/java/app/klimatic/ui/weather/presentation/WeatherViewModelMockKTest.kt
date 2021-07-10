package app.klimatic.ui.weather.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.klimatic.data.remote.weather.WeatherResponse
import app.klimatic.data.response.Response
import app.klimatic.ui.utils.ViewState
import app.klimatic.utils.TestCoroutineRule
import app.klimatic.utils.factory.TestFactory
import app.klimatic.utils.robots.WeatherViewModelMockKTestRobot
import com.google.common.truth.Truth.assertThat
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherViewModelMockKTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var robot: WeatherViewModelMockKTestRobot

    @Before
    fun setUp() {
        robot = WeatherViewModelMockKTestRobot().apply {
            setUp()
        }
    }

    @After
    fun tearDown() {
        robot.tearDown()
    }

    @Test
    fun `GIVEN query WHEN fetch current weather api RETURNS current weather response WHILE fetching current weather THEN post success`()= robot.run {
        val weatherSlot = slot<ViewState.Success<WeatherResponse>>()
        val query = "xx"
        val expectValue = TestFactory.currentWeatherResponse
        coEveryFetchWeatherApiReturns(value = Response.Success(data = expectValue))
        testCoroutineRule.runBlockingTest {
            viewModel.fetchWeatherRemote(query = query)
            verify {
                verifyWeatherObserver(value = capture(weatherSlot))
            }
        }
        val actualValue = weatherSlot.captured
        assertThat(actualValue.data.current).isEqualTo(expectValue.current)
        assertThat(actualValue.data.location).isEqualTo(expectValue.location)
    }

    @Test
    fun `WHEN fetch current weather api RETURNS current weather response WHILE fetching current weather THEN post success`() = robot.run {
        val weatherSlot = slot<ViewState.Success<WeatherResponse>>()
        val expectValue = TestFactory.currentWeatherResponse
        coEveryFetchWeatherApiReturns(value = Response.Success(data = expectValue))
        testCoroutineRule.runBlockingTest {
            viewModel.fetchWeatherRemote()
            verify {
                verifyWeatherObserver(value = capture(weatherSlot))
            }
        }
        val actualValue = weatherSlot.captured
        assertThat(actualValue.data.current).isEqualTo(expectValue.current)
        assertThat(actualValue.data.location).isEqualTo(expectValue.location)
    }

    @Test
    fun `GIVEN query WHEN fetch current weather api RETURNS error WHILE fetching current weather THEN show error`() = robot.run {
        val query = "xx"
        coEveryFetchWeatherApiReturns(value = Response.Error(TestFactory.ERROR_CODE_GENERIC))
        testCoroutineRule.runBlockingTest {
            viewModel.fetchWeatherRemote(query = query)
            verify {
                verifyWeatherObserver(value = ViewState.Error(TestFactory.ERROR_CODE_GENERIC))
            }
        }
    }

    @Test
    fun `WHEN fetch current weather api RETURNS error WHILE fetching current weather THEN show error`() = robot.run {
        coEveryFetchWeatherApiReturns(value = Response.Error(TestFactory.ERROR_CODE_GENERIC))
        testCoroutineRule.runBlockingTest {
            viewModel.fetchWeatherRemote()
            verify {
                verifyWeatherObserver(value = ViewState.Error(TestFactory.ERROR_CODE_GENERIC))
            }
        }
    }

}
