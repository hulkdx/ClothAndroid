package hulkdx.com.features.profile.view

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.core.android.applicationComponent
import hulkdx.com.core.android.util.observeFragment

import hulkdx.com.core.android.view.fragments.BaseFragment
import hulkdx.com.features.profile.R
import hulkdx.com.features.profile.di.DaggerProfileComponent
import hulkdx.com.features.profile.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 24/08/2019.
 */
class ProfileFragment : BaseFragment() {

    private lateinit var mProfileViewModel: ProfileViewModel

    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
    }

    // endregion SetupUI ---------------------------------------------------------------------------

    override fun setupViewModel() {
        mProfileViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProfileViewModel::class.java)
        // mProfileViewModel.TODOLiveData().observeFragment(this, Observer {
        //     when (it) {
        //     }
        // })
    }

    // region TODO Callback ------------------------------------------------------------------------

    // endregion TODO Callback ---------------------------------------------------------------------
    // region Extra functions ----------------------------------------------------------------------

    override fun inject(context: Context) {
        DaggerProfileComponent.builder()
                .context(context)
                .applicationComponent(applicationComponent(context))
                .build()
                .inject(this)
    }

    override fun fragmentLayout(): Int {
        return R.layout.fragment_profile
    }

    // endregion Extra functions -------------------------------------------------------------------
}
    