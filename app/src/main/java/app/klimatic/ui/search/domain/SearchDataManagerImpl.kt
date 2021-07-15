package app.klimatic.ui.search.domain

import app.klimatic.data.model.weather.Location
import app.klimatic.data.remote.service.SearchService
import app.klimatic.data.response.Response
import app.klimatic.ui.utils.getResponse

class SearchDataManagerImpl(
    private val searchService: SearchService
) : SearchDataManager {

    override suspend fun searchLocation(
        query: String
    ): Response<List<Location>> = getResponse {
        searchService.searchLocation(query)
    }
}
