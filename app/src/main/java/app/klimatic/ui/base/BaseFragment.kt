package app.klimatic.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import app.klimatic.KlimaticApplication
import app.klimatic.di.components.ActivityComponent
import app.klimatic.di.components.DaggerActivityComponent

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityComponent = DaggerActivityComponent.builder()
            .applicationComponent(
                (requireActivity().application as KlimaticApplication).getApplicationComponent()
            )
            .build()

        performDependencyInjection(activityComponent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getLayoutResource(), container, false)
        setupView(view, savedInstanceState)
        return view
    }

    @LayoutRes
    abstract fun getLayoutResource(): Int

    abstract fun performDependencyInjection(activityComponent: ActivityComponent)

    abstract fun setupView(view: View, savedInstanceState: Bundle?)
}
