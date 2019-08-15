package hulkdx.com.features.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hulkdx.com.domain.entities.UserEntity
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 14/07/2019.
 */

class AuthViewModel @Inject constructor(
): ViewModel() {

    private val mLoginLiveData = MutableLiveData<LoginResults>()

    // region LiveData Setup -----------------------------------------------------------------------

    fun login(): LiveData<LoginResults> = mLoginLiveData

    // endregion LiveData Setup --------------------------------------------------------------------
    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
    }

    // endregion Extra -----------------------------------------------------------------------------
    // region Results ------------------------------------------------------------------------------

    sealed class LoginResults {
        class Success(val user: UserEntity): LoginResults()
        object NetworkError: LoginResults()
        object GeneralError: LoginResults()
        object AuthError: LoginResults()
    }

    // endregion Results ---------------------------------------------------------------------------

}
