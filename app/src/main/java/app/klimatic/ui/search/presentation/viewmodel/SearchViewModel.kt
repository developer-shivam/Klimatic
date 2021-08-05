package app.klimatic.ui.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.klimatic.data.model.weather.Location
import app.klimatic.data.pref.AppSharedPreferences
import app.klimatic.data.response.Response
import app.klimatic.ui.base.BaseCurrentSelectedLocationViewModel
import app.klimatic.ui.search.domain.SearchDataManager
import app.klimatic.ui.utils.ViewState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel(
    appSharedPreferences: AppSharedPreferences,
    private val dataManager: SearchDataManager
) : BaseCurrentSelectedLocationViewModel(appSharedPreferences) {

    private val locationsLiveData: MutableLiveData<ViewState<List<Location>>> = MutableLiveData()
    val locations: LiveData<ViewState<List<Location>>> = locationsLiveData

    private var searchJob: Job? = null

    init {
        // fetch location initially with auto:ip
        searchLocationByQuery()
    }

    fun searchLocationByLatLon(lat: Double, long: Double) {
        searchLocation(query = "$lat,$long")
    }

    fun searchLocationByQuery(query: String = DEFAULT_QUERY) {
        searchLocation(query = query)
    }

    private fun searchLocation(query: String) {
        searchJob?.cancel()
        searchJob = ioScope.launch {
            if (query != DEFAULT_QUERY) {
                delay(300)
            }
            fetchData(query)
        }
    }

    private suspend fun fetchData(query: String) {
        val viewState: ViewState<List<Location>> =
            when (val response =
                dataManager.searchLocation(query)) {
                is Response.Success -> {
                    ViewState.Success(data = response.data)
                }
                is Response.Error -> ViewState.Error(code = response.errorCode)
            }
        locationsLiveData.postValue(viewState)
    }
}
