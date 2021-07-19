package app.klimatic.ui.settings.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import app.klimatic.R
import app.klimatic.data.pref.AppSharedPreferences
import app.klimatic.ui.base.BaseFragment
import app.klimatic.ui.home.HomeActivity
import app.klimatic.ui.utils.Theme
import kotlinx.android.synthetic.main.fragment_settings.rvTheme
import org.koin.android.ext.android.inject

class SettingsFragment : BaseFragment() {

    private val appPreferences: AppSharedPreferences by inject()

    override fun getLayoutResource(): Int = R.layout.fragment_settings

    override fun setupView(view: View, savedInstanceState: Bundle?) {

        val themes = listOf(
            Theme.Blue(),
            Theme.Red(),
            Theme.Green(),
            Theme.Orange(),
            Theme.Purple()
        )

        rvTheme.run {

            adapter = ThemeAdapter(
                themes
            ) { selectedTheme ->
                appPreferences.setSelectedThemeId(selectedTheme.id)
                HomeActivity.launchSingleTask(requireContext())
            }

            layoutManager = GridLayoutManager(context, 5)
        }
    }
}
