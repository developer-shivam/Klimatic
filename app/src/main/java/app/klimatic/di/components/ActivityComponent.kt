package app.klimatic.di.components

import app.klimatic.di.scopes.ActivityScope
import app.klimatic.ui.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
}