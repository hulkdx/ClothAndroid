package hulkdx.com.features.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hulkdx.com.domain.entities.UserGender
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

class AuthViewModel @Inject constructor(
    private val mRegisterAuthUseCase: RegisterAuthUseCase
): ViewModel() {

//    private val mLoginLiveData = MutableLiveData<LoginResults>()
    private val mRegisterLiveData = MutableLiveData<RegisterAuthUseCase.Result>()

    // region LiveData Setup -----------------------------------------------------------------------

//    fun loginLiveData(): LiveData<LoginResults> = mLoginLiveData
    fun registerLiveData(): LiveData<RegisterAuthUseCase.Result> = mRegisterLiveData

    // endregion LiveData Setup --------------------------------------------------------------------

    fun register(email: String, password: String, firstName: String, lastName: String, gender: UserGender) {
        mRegisterAuthUseCase.register(
                RegisterAuthUseCase.Params(email, password, firstName, lastName, gender),
                callback = {
                    mRegisterLiveData.value = it
                })
    }

    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
        mRegisterAuthUseCase.dispose()
    }

    // endregion Extra -----------------------------------------------------------------------------
    // region Results ------------------------------------------------------------------------------


    // endregion Results ---------------------------------------------------------------------------

}
