package app.klimatic.data.remote.service

import app.klimatic.data.model.weather.Location
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/v1/search.json")
    suspend fun searchLocation(
        @Query("q") query: String
    ): List<Location>
}
