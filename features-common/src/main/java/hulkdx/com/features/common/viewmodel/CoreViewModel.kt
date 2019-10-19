package hulkdx.com.features.common.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hulkdx.com.domain.interactor.auth.user.GetUserUseCase
import hulkdx.com.domain.interactor.auth.user.GetUserUseCase.Result
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/07/2019.
 */
class CoreViewModel @Inject constructor(
        private val mGetUserUseCase: GetUserUseCase
): ViewModel() {

    private val mUserResult = MutableLiveData<Result>(Result.Loading)

    // region User ---------------------------------------------------------------------------------

    fun logout() {
        mUserResult.value = Result.InvalidUser
    }

    fun fetchUserInfo() {
        mGetUserUseCase.fetchUserInfo {
            mUserResult.value = it
        }
    }

    fun getUserLiveData() = mUserResult

    // endregion User ------------------------------------------------------------------------------
    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
        mGetUserUseCase.dispose()
    }

    // endregion Extra -----------------------------------------------------------------------------

}