package hulkdx.com.features.auth.view.register

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import hulkdx.com.core.android.applicationComponent
import hulkdx.com.core.android.util.observeFragment

import hulkdx.com.core.android.view.fragments.BaseFragment
import hulkdx.com.core.android.viewmodel.AuthCommonViewModel
import hulkdx.com.domain.entities.UserGender
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import hulkdx.com.features.auth.BuildConfig
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
        val itemPosition = genderSpinner.selectedItemPosition
        registerLoading()
        mAuthViewModel.register(
                email,
                password,
                firstName,
                lastName,
                UserGender.convert(itemPosition)
        )
    }

    // endregion SetupUI ---------------------------------------------------------------------------

    override fun setupViewModel() {
        mAuthCommonViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AuthCommonViewModel::class.java)
        mAuthViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AuthViewModel::class.java)
        mAuthViewModel.registerLiveData().observeFragment(this, Observer {
            when (it) {
                is RegisterAuthUseCase.Result.Success -> registerSuccess()
                is RegisterAuthUseCase.Result.InvalidEmailAddress -> registerError()
                is RegisterAuthUseCase.Result.WeakPassword -> registerError()
                is RegisterAuthUseCase.Result.AccountExists -> registerError()
                is RegisterAuthUseCase.Result.GeneralError -> {
                    showThrowableDebug(it.throwable)
                    registerError()
                }
            }
        })
    }

    // region Register Callback---------------------------------------------------------------------

    private fun registerLoading() {
        detailsTextView.text = "loading"
    }

    private fun registerSuccess() {
        mNavigationManager.navigateToExplore()
    }

    private fun registerError() {
        detailsTextView.text = "error"
    }

    // endregion Register Callback------------------------------------------------------------------
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

    private fun showThrowableDebug(throwable: Throwable?) {
        if (throwable != null && BuildConfig.DEBUG) {
            Toast.makeText(context, throwable.toString(), Toast.LENGTH_LONG).show()
        }
    }

    // endregion Extra functions -------------------------------------------------------------------
}
