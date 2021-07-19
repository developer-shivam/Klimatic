package app.klimatic.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    private var rootView: View? = null

    private var isViewReused: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * No need to re-create the view.
         * Refer to https://twitter.com/ianhlake/status/1103522856535638016
         * */
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResource(), container, false)
        } else {
            isViewReused = true
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!isViewReused) {
            setupView(view, savedInstanceState)
        }
    }

    @LayoutRes
    abstract fun getLayoutResource(): Int

    open fun setupView(view: View, savedInstanceState: Bundle?) {
    }
}
