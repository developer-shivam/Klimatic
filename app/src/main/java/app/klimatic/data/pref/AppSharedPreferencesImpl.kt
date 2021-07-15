package app.klimatic.data.pref

import android.content.Context
import android.content.SharedPreferences

class AppSharedPreferencesImpl(
    name: String,
    context: Context
) : AppSharedPreferences {

    companion object {
        const val KEY_CURRENT_SELECTED_LOCATION = "KEY_CURRENT_SELECTED_LOCATION"
    }

    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    override fun setCurrentSelectedLocation(location: String) {
        sharedPreferences.edit()
            .putString(KEY_CURRENT_SELECTED_LOCATION, location)
            .apply()
    }

    override fun getCurrentSelectedLocation(): String? {
        return sharedPreferences.getString(KEY_CURRENT_SELECTED_LOCATION, null)
    }
}
