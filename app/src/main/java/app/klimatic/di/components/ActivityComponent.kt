package app.klimatic.di.components

import app.klimatic.di.modules.ActivityModule
import app.klimatic.di.scopes.ActivityScope
import app.klimatic.ui.weather.presentation.WeatherActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: WeatherActivity)
}