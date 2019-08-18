package hulkdx.com.features.auth.view.register

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.core.android.applicationComponent

import hulkdx.com.core.android.view.fragments.BaseFragment
import hulkdx.com.core.android.viewmodel.AuthCommonViewModel
import hulkdx.com.features.auth.R
import hulkdx.com.features.auth.di.DaggerAuthComponent
import hulkdx.com.features.auth.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
class RegisterFragment : BaseFragment(), View.OnClickListener {

    private lateinit var mAuthCommonViewModel: AuthCommonViewModel
    private lateinit var mAuthViewModel: AuthViewModel

    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
        registerButton.setOnClickListener(this)
    }

    override fun setupViewModel() {
        mAuthCommonViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AuthCommonViewModel::class.java)
        mAuthViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AuthViewModel::class.java)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.registerButton -> {
                onRegisterButtonClick()
            }
        }
    }

    private fun onRegisterButtonClick() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        mAuthViewModel.register(email, password, firstName, lastName)
    }

    // endregion SetupUI ---------------------------------------------------------------------------
    // region Extra functions ----------------------------------------------------------------------

    override fun inject(context: Context) {
        DaggerAuthComponent.builder()
                .context(context)
                .applicationComponent(applicationComponent(context))
                .build()
                .inject(this)
    }

    override fun fragmentLayout(): Int {
        return R.layout.fragment_register
    }

    // endregion Extra functions -------------------------------------------------------------------
}
