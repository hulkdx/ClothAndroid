package hulkdx.com.features.auth.view.login

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.core.android.view.fragments.BaseFragment
import hulkdx.com.core.android.viewmodel.CoreViewModel
import hulkdx.com.features.auth.R
import hulkdx.com.features.auth.di.AuthComponent
import hulkdx.com.features.auth.di.getAuthComponent
import hulkdx.com.features.auth.viewmodel.AuthViewModel

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
class LoginFragment: BaseFragment() {

    private lateinit var mCoreViewModel: CoreViewModel
    private lateinit var mAuthViewModel: AuthViewModel

    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
    }

    // endregion SetupUI ---------------------------------------------------------------------------

    override fun setupViewModel() {
        mCoreViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CoreViewModel::class.java)
        mAuthViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AuthViewModel::class.java)
    }

    // region Extra functions ----------------------------------------------------------------------

    override fun inject(context: Context) {
        getAuthComponent(context)
                .inject(this)
    }

    override fun fragmentLayout(): Int {
        return R.layout.fragment_login
    }

    // endregion Extra functions -------------------------------------------------------------------
}
