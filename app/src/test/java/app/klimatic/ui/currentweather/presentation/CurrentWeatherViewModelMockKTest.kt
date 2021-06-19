package app.klimatic.ui.currentweather.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.klimatic.data.remote.weather.CurrentWeatherResponse
import app.klimatic.data.response.Response
import app.klimatic.ui.utils.ViewState
import app.klimatic.utils.TestCoroutineRule
import app.klimatic.utils.factory.TestFactory
import app.klimatic.utils.robots.CurrentWeatherViewModelMockKTestRobot
import com.google.common.truth.Truth.assertThat
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CurrentWeatherViewModelMockKTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var robot: CurrentWeatherViewModelMockKTestRobot

    @Before
    fun setUp() {
        robot = CurrentWeatherViewModelMockKTestRobot().apply {
            setUp()
        }
    }

    @After
    fun tearDown() {
        robot.tearDown()
    }

    @Test
    fun `GIVEN query WHEN fetch current weather api RETURNS current weather response WHILE fetching current weather THEN post success`()= robot.run {
        val weatherSlot = slot<ViewState.Success<CurrentWeatherResponse>>()
        val query = "xx"
        val expectValue = TestFactory.currentWeatherResponse
        coEveryFetchCurrentWeatherApiReturns(value = Response.Success(data = expectValue))
        testCoroutineRule.runBlockingTest {
            viewModel.fetchCurrentWeather(query = query)
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
        val weatherSlot = slot<ViewState.Success<CurrentWeatherResponse>>()
        val expectValue = TestFactory.currentWeatherResponse
        coEveryFetchCurrentWeatherApiReturns(value = Response.Success(data = expectValue))
        testCoroutineRule.runBlockingTest {
            viewModel.fetchCurrentWeather()
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
        coEveryFetchCurrentWeatherApiReturns(value = Response.Error(TestFactory.ERROR_CODE_GENERIC))
        testCoroutineRule.runBlockingTest {
            viewModel.fetchCurrentWeather(query = query)
            verify {
                verifyWeatherObserver(value = ViewState.Error(TestFactory.ERROR_CODE_GENERIC))
            }
        }
    }

    @Test
    fun `WHEN fetch current weather api RETURNS error WHILE fetching current weather THEN show error`() = robot.run {
        coEveryFetchCurrentWeatherApiReturns(value = Response.Error(TestFactory.ERROR_CODE_GENERIC))
        testCoroutineRule.runBlockingTest {
            viewModel.fetchCurrentWeather()
            verify {
                verifyWeatherObserver(value = ViewState.Error(TestFactory.ERROR_CODE_GENERIC))
            }
        }
    }

}
