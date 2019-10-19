package hulkdx.com.features.auth.view.login

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.features.common.util.observeFragment
import hulkdx.com.features.common.view.fragments.BaseFragment
import hulkdx.com.domain.interactor.auth.login.LoginAuthUseCase
import hulkdx.com.features.auth.R
import hulkdx.com.features.auth.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.passwordEditText
import kotlinx.android.synthetic.main.fragment_login.registerButton

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
class LoginFragment: BaseFragment() {

    private lateinit var mAuthViewModel: AuthViewModel

    // region SetupUI ------------------------------------------------------------------------------

    override fun setupUI() {
        registerButton.setOnClickListener {
            mNavigationManager.navigateToRegister(true)
        }
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            mAuthViewModel.login(username, password)
        }
    }

    // endregion SetupUI ---------------------------------------------------------------------------

    override fun setupViewModel() {
        super.setupViewModel()
        mAuthViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AuthViewModel::class.java)
        mAuthViewModel.loginLiveData().observeFragment(this, Observer { result ->
            when (result) {
                is LoginAuthUseCase.Result.Loading       -> loginLoading()
                is LoginAuthUseCase.Result.Success       -> loginSuccess()
                is LoginAuthUseCase.Result.WrongEmail    -> loginError()
                is LoginAuthUseCase.Result.WrongPassword -> loginError()
                is LoginAuthUseCase.Result.GeneralError  -> loginError()
            }
        })
    }

    // region Login Callback---------------------------------------------------------------------

    private fun loginLoading() {
        detailsTextView.text = "Loading"
    }

    private fun loginSuccess() {
        mNavigationManager.navigateToExplore()
    }

    private fun loginError() {
        detailsTextView.text = "Error"
    }

    // endregion Login Callback------------------------------------------------------------------
    // region Extra functions ----------------------------------------------------------------------

    override fun fragmentLayout(): Int {
        return R.layout.fragment_login
    }

    // endregion Extra functions -------------------------------------------------------------------
}
