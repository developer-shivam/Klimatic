package app.klimatic.di.modules

import android.content.Context
import app.klimatic.data.pref.AppSharedPreferences
import app.klimatic.data.pref.AppSharedPreferencesImpl
import org.koin.dsl.module

const val SHARED_PREFERENCE_NAME = "Klimatic-Shared-Pref"

val preferenceModule = module {
    single { provideAppSharedPreferences(get()) }
}

private fun provideAppSharedPreferences(context: Context): AppSharedPreferences {
    return AppSharedPreferencesImpl(SHARED_PREFERENCE_NAME, context)
}
