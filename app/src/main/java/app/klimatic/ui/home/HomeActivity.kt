package app.klimatic.ui.home

import android.os.Bundle
import app.klimatic.R
import app.klimatic.di.components.ActivityComponent
import app.klimatic.ui.base.BaseActivity
import app.klimatic.ui.currentweather.presentation.CurrentWeatherFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    override fun getLayoutResource(): Int = R.layout.activity_home

    override fun performDependencyInjection(activityComponent: ActivityComponent) {
        // nothing to be injected
    }

    override fun setupView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(container.id, CurrentWeatherFragment.create())
            transaction.commit()
        }
    }
}