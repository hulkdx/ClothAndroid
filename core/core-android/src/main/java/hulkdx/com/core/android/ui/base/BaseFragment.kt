package hulkdx.com.core.android.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.core.android.util.ViewModelFactory
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */

abstract class BaseFragment<VM: ViewModel>: Fragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory
    lateinit var mViewModel: VM

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
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(getViewModelClass())
        setupViewModel()
    }

    protected abstract fun inject(context: Context)
    protected abstract fun getViewModelClass(): Class<VM>
    protected abstract fun fragmentLayout(): Int
    protected abstract fun setupUI()
    protected abstract fun setupViewModel()
}
