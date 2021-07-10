package app.klimatic.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())

        setupView(savedInstanceState)
    }

    @LayoutRes
    abstract fun getLayoutResource(): Int

    abstract fun setupView(savedInstanceState: Bundle?)
}
