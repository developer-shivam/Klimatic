package app.klimatic.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import app.klimatic.R
import app.klimatic.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.bottom_navigation_view

class HomeActivity : BaseActivity() {

    companion object {
        fun launchSingleTask(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }

    override fun getLayoutResource(): Int = R.layout.activity_home

    override fun setupView(savedInstanceState: Bundle?) {
        bottom_navigation_view.setupWithNavController(navigationController())
        // Turn off reselection
        bottom_navigation_view.setOnItemReselectedListener {
            // no-op
        }
    }

    private fun navigationController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }
}
