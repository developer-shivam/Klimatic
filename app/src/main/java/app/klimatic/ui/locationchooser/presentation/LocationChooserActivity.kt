package app.klimatic.ui.locationchooser.presentation

import android.os.Bundle
import app.klimatic.R
import app.klimatic.ui.base.BaseActivity

class LocationChooserActivity : BaseActivity() {

    override fun getLayoutResource(): Int = R.layout.activity_location_chooser

    override fun setupView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view, LocationChooserFragment())
                .commit()
        }
    }
}
