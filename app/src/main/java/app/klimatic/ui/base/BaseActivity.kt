package app.klimatic.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import app.klimatic.data.pref.AppSharedPreferences
import app.klimatic.ui.utils.ThemeUtility
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    private val appPreferences: AppSharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtility(appPreferences).applyTheme(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())

        setupView(savedInstanceState)
    }

    @LayoutRes
    abstract fun getLayoutResource(): Int

    abstract fun setupView(savedInstanceState: Bundle?)
}
