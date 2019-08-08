package hulkdx.com.core.android.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.core.android.util.ViewModelHelper
import hulkdx.com.core.android.viewmodel.AuthViewModel
import hulkdx.com.core.android.viewmodel.ViewModelFactory
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */

abstract class BaseFragment: Fragment() {

    @Inject
    lateinit var mViewModelHelper: ViewModelHelper

    override fun onAttach(context: Context?) {
        inject(requireContext())
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragmentLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
    }

    protected abstract fun inject(context: Context)
    protected abstract fun fragmentLayout(): Int
    protected abstract fun setupUI()
    protected abstract fun setupViewModel()
}
