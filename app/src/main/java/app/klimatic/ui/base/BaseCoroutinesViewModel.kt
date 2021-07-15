package app.klimatic.ui.base

import androidx.lifecycle.ViewModel
import app.klimatic.data.pref.AppSharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class BaseCoroutinesViewModel(
    private val appSharedPreferences: AppSharedPreferences
) : ViewModel() {

    companion object {
        const val DEFAULT_QUERY = "auto:ip"
    }

    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by this ViewModel.
     * Since we pass viewModelJob, you can cancel all coroutines
     * launched by ioScope by calling viewModelJob.cancel()
     */
    var ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }

    fun getCurrentSelectedLocation(): String? =
        appSharedPreferences.getCurrentSelectedLocation()

    fun setCurrentSelectedLocation(location: String) =
        appSharedPreferences.setCurrentSelectedLocation(location)
}
