package hulkdx.com.features.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hulkdx.com.domain.entities.UserGender
import hulkdx.com.domain.interactor.auth.login.LoginAuthUseCase
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

internal class AuthViewModel @Inject constructor(
    private val mRegisterAuthUseCase: RegisterAuthUseCase,
    private val mLoginAuthUseCase: LoginAuthUseCase
): ViewModel() {

    private val mLoginLiveData = MutableLiveData<LoginAuthUseCase.Result>()
    private val mRegisterLiveData = MutableLiveData<RegisterAuthUseCase.Result>()

    // region LiveData Setup -----------------------------------------------------------------------

    fun loginLiveData(): LiveData<LoginAuthUseCase.Result> = mLoginLiveData
    fun registerLiveData(): LiveData<RegisterAuthUseCase.Result> = mRegisterLiveData

    // endregion LiveData Setup --------------------------------------------------------------------

    fun register(email: String, password: String, firstName: String, lastName: String, gender: UserGender) {
        mRegisterAuthUseCase.register(RegisterAuthUseCase.Params(email, password, firstName, lastName, gender)) {
            mRegisterLiveData.value = it
        }
    }

    fun login(username: String, password: String) {
        mLoginAuthUseCase.login(username, password) {
            mLoginLiveData.value = it
        }
    }

    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
        mRegisterAuthUseCase.dispose()
        mLoginAuthUseCase.dispose()
    }

    // endregion Extra -----------------------------------------------------------------------------
}
