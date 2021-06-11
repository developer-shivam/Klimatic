package app.klimatic.di.components

import app.klimatic.di.modules.ActivityModule
import app.klimatic.di.scopes.ActivityScope
import app.klimatic.ui.currentweather.presentation.CurrentWeatherFragment
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(currentWeatherFragment: CurrentWeatherFragment)
}