package app.klimatic.ui.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.klimatic.data.model.weather.Location
import app.klimatic.data.pref.AppSharedPreferences
import app.klimatic.data.response.Response
import app.klimatic.ui.base.BaseCoroutinesViewModel
import app.klimatic.ui.search.domain.SearchDataManager
import app.klimatic.ui.utils.ViewState
import kotlinx.coroutines.launch

class SearchViewModel(
    appSharedPreferences: AppSharedPreferences,
    private val dataManager: SearchDataManager
) : BaseCoroutinesViewModel(appSharedPreferences) {

    private val locationsLiveData: MutableLiveData<ViewState<List<Location>>> = MutableLiveData()
    val locations: LiveData<ViewState<List<Location>>> = locationsLiveData

    init {
        // fetch location initially with auto:ip
        searchLocation()
    }

    fun searchLocation(query: String = DEFAULT_QUERY) {
        ioScope.launch {
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
}
