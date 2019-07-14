package hulkdx.com.features.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.core.android.MainApplication
import hulkdx.com.core.android.applicationComponent
import hulkdx.com.core.android.util.ViewModelFactory
import hulkdx.com.features.home.R
import hulkdx.com.features.home.di.DaggerHomeComponent
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

class HomeFragment: Fragment() {

    // region Lifecycle ----------------------------------------------------------------------------
    @Inject lateinit var mViewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ViewModelProviders.of(requireActivity(), mViewModelFactory).get(HomeViewModel::class.java)
    }

    // endregion Lifecycle -------------------------------------------------------------------------

    private fun inject() {
        DaggerHomeComponent.builder()
                .applicationComponent(applicationComponent(requireContext()))
                .build()
    }
}