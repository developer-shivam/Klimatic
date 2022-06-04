package app.klimatic.ui.settings.presentation

import android.os.Bundle
import app.klimatic.R
import app.klimatic.ui.base.BaseActivity

class SettingsActivity : BaseActivity() {

    override fun getLayoutResource(): Int = R.layout.activity_settings

    override fun setupView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, SettingsFragment())
                .commit()
        }
    }
}
