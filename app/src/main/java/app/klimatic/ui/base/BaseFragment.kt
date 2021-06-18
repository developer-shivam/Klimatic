package app.klimatic.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

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

    abstract fun setupView(view: View, savedInstanceState: Bundle?)
}
