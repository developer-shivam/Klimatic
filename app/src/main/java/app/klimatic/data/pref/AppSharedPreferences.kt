package app.klimatic.data.pref

interface AppSharedPreferences {

    fun setCurrentSelectedLocation(location: String)

    fun getCurrentSelectedLocation(): String?
}
