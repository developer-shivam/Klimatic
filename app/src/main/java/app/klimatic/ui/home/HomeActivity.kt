package app.klimatic.ui.home

import android.os.Bundle
import app.klimatic.R
import app.klimatic.ui.base.BaseActivity
import app.klimatic.ui.weather.presentation.WeatherFragment
import kotlinx.android.synthetic.main.activity_home.container

class HomeActivity : BaseActivity() {

    override fun getLayoutResource(): Int = R.layout.activity_home

    override fun setupView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(container.id, WeatherFragment.create())
            transaction.commit()
        }
    }
}
