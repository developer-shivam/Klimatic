package app.klimatic.ui.weather.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.klimatic.data.model.local.WeatherDao
import app.klimatic.data.remote.service.WeatherService
import app.klimatic.data.response.Response
import app.klimatic.ui.utils.ErrorUtils
import app.klimatic.utils.MockWebServerBaseTest
import app.klimatic.utils.factory.TestFactory.EMPTY_QUERY
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.net.HttpURLConnection.HTTP_OK

@RunWith(JUnit4::class)
class WeatherDataManagerTest : MockWebServerBaseTest() {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dao: WeatherDao

    private lateinit var weatherService: WeatherService

    // Subject under test
    private lateinit var weatherDataManager: WeatherDataManager

    @Before
    fun start() {
        MockitoAnnotations.initMocks(this)
        weatherService = provideTestWeatherService()
        weatherDataManager = WeatherDataManagerImpl(dao, weatherService)
    }

    @Test
    fun givenSuccessResponse_whenFetchCurrentWeather_shouldReturnSuccess() {
        runBlocking {

            // Given
            mockHttpResponseWithUri("json/success_response.json", HTTP_OK)

            // When
            val actual =
                weatherDataManager.fetchWeatherRemote(EMPTY_QUERY)

            // Then
            assertTrue(actual is Response.Success)
            actual as Response.Success
            assertEquals(actual.data.location?.country, "India")
            assertEquals(actual.data.current?.tempC, 33.toDouble())
        }
    }

    @Test
    fun givenNoResponse_whenFetchCurrentWeather_shouldReturnError() {
        runBlocking {

            // Given
            mockHttpResponse("", HTTP_OK)

            // When
            val actual =
                weatherDataManager.fetchWeatherRemote(EMPTY_QUERY)

            // Then
            assertTrue(actual is Response.Error)
            actual as Response.Error
            assertEquals(actual.errorCode, ErrorUtils.ERROR_CODE_GENERIC)
        }
    }

}