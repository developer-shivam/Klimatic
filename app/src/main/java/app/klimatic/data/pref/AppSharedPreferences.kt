package app.klimatic.data.pref

interface AppSharedPreferences {

    fun setSelectedThemeId(id: Int)

    fun getSelectedThemeId(): Int

    fun setCurrentSelectedLocation(location: String)

    fun getCurrentSelectedLocation(): String?
}
