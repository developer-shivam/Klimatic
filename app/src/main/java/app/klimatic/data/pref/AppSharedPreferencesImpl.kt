package app.klimatic.data.pref

import android.content.Context
import android.content.SharedPreferences
import app.klimatic.ui.utils.Theme

class AppSharedPreferencesImpl(
    name: String,
    context: Context
) : AppSharedPreferences {

    companion object {
        const val KEY_SELECTED_THEME_ID = "KEY_SELECTED_THEME_ID"
        const val KEY_CURRENT_SELECTED_LOCATION = "KEY_CURRENT_SELECTED_LOCATION"
    }

    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    override fun setSelectedThemeId(id: Int) {
        sharedPreferences.edit()
            .putInt(KEY_SELECTED_THEME_ID, id)
            .apply()
    }

    override fun getSelectedThemeId(): Int {
        return sharedPreferences.getInt(KEY_SELECTED_THEME_ID, Theme.Red().id)
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
