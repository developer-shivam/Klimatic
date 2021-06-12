package app.klimatic.utils

import app.klimatic.data.remote.weather.CurrentWeatherResponse

object TestFactory {
    const val MOCKED_ERROR_CODE: Int = 0
    val currentWeatherResponse = CurrentWeatherResponse(null, null)
    const val ERROR_CODE_GENERIC = 100
}
