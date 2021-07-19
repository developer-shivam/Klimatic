package app.klimatic.utils.factory

import app.klimatic.data.remote.weather.WeatherResponse

object TestFactory {
    const val EMPTY_QUERY: String = ""
    const val TEST_SAVED_LOCATION: String = ""
    const val MOCKED_ERROR_CODE: Int = 0
    val currentWeatherResponse = WeatherResponse(null, null, null)
    const val ERROR_CODE_GENERIC = 100
}
