package app.klimatic.ui.currentweather.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.klimatic.data.remote.weather.CurrentWeatherService
import app.klimatic.data.response.Response
import app.klimatic.ui.utils.ErrorUtils
import app.klimatic.utils.MockWebServerBaseTest
import app.klimatic.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection.HTTP_OK

@RunWith(JUnit4::class)
class CurrentWeatherDataRepositoryTest : MockWebServerBaseTest() {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var currentWeatherService: CurrentWeatherService

    // Subject under test
    private lateinit var currentWeatherDataRepository: CurrentWeatherDataRepository

    @Before
    fun start() {
        currentWeatherService = provideTestCurrentWeatherService()
        currentWeatherDataRepository = CurrentWeatherDataRepositoryImpl(currentWeatherService)
    }

    @Test
    fun givenSuccessResponse_whenFetchCurrentWeather_shouldReturnSuccess() {
        runBlocking {
            mockHttpResponseWithUri("json/success_response.json", HTTP_OK)

            val actual =
                currentWeatherDataRepository.fetchCurrentWeather(anyString())

            assertTrue(actual is Response.Success)
            actual as Response.Success
            assertEquals(actual.data.location?.country, "India")
            assertEquals(actual.data.current?.tempC, 33.toDouble())
        }
    }

    @Test
    fun givenNoResponse_whenFetchCurrentWeather_shouldReturnError() {
        runBlocking {
        mockHttpResponse("", HTTP_OK)

            val actual =
                currentWeatherDataRepository.fetchCurrentWeather(anyString())

            assertTrue(actual is Response.Error)
            actual as Response.Error
            assertEquals(actual.errorCode, ErrorUtils.ERROR_CODE_GENERIC)
        }
    }

}