package app.klimatic.data.remote.weather

import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherService {

    @GET("/v1/current.json")
    suspend fun fetchCurrentWeather(
        @Query("q") query: String?
    ) : CurrentWeatherResponse
}