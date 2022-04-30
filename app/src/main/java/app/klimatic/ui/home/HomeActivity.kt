package app.klimatic.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import app.klimatic.R
import app.klimatic.ui.base.BaseActivity
import app.klimatic.ui.locationchooser.presentation.LocationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity() {

    // Shared object between all fragment
    private val locationViewModel by viewModel<LocationViewModel>()

    companion object {
        fun launchSingleTask(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }

    override fun getLayoutResource(): Int = R.layout.activity_home

    override fun setupView(savedInstanceState: Bundle?) {
    }
}
