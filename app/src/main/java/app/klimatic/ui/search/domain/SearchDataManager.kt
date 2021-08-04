package app.klimatic.ui.search.domain

import app.klimatic.data.model.weather.Location
import app.klimatic.data.response.Response

interface SearchDataManager {
    suspend fun searchLocation(query: String): Response<List<Location>>
}
