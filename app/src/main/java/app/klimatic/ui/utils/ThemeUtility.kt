package app.klimatic.ui.utils

import androidx.appcompat.app.AppCompatActivity
import app.klimatic.data.pref.AppSharedPreferences

class ThemeUtility(
    private val appSharedPreferences: AppSharedPreferences
) {

    fun applyTheme(activity: AppCompatActivity) {
        val id = appSharedPreferences.getSelectedThemeId()
        activity.setTheme(Theme.getStyle(Theme.getTheme(id)))
    }
}
