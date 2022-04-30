package app.klimatic.ui.locationchooser.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.klimatic.data.pref.AppSharedPreferences

class LocationViewModel(
    private val appSharedPreferences: AppSharedPreferences
) : ViewModel() {

    private val currentSelectedLocationLiveData: MutableLiveData<String?> = MutableLiveData()
    val currentSelectedLocation: LiveData<String?> = currentSelectedLocationLiveData

    fun fetchCurrentSelectedLocation() {
        currentSelectedLocationLiveData.value = getCurrentSelectedLocation()
    }

    private fun getCurrentSelectedLocation(): String? =
        appSharedPreferences.getCurrentSelectedLocation()

    fun setCurrentSelectedLocation(location: String) {
        appSharedPreferences.setCurrentSelectedLocation(location)
        currentSelectedLocationLiveData.value = getCurrentSelectedLocation()
    }
}
