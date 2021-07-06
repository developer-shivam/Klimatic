package app.klimatic.data.remote.service

import app.klimatic.data.remote.weather.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/v1/forecast.json")
    suspend fun fetchWeather(
        @Query("q") query: String?
    ): WeatherResponse
}
