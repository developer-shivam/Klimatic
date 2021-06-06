package app.klimatic.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import app.klimatic.KlimaticApplication
import app.klimatic.di.components.ActivityComponent
import app.klimatic.di.components.DaggerActivityComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())

        val activityComponent = DaggerActivityComponent.builder()
            .applicationComponent((application as KlimaticApplication).getApplicationComponent())
            .build()

        performDependencyInjection(activityComponent)

        setupView(savedInstanceState)
    }

    @LayoutRes
    abstract fun getLayoutResource() : Int

    abstract fun performDependencyInjection(activityComponent: ActivityComponent)

    abstract fun setupView(savedInstanceState: Bundle?)
}