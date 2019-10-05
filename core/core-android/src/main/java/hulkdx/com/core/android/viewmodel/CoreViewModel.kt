package hulkdx.com.core.android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.interactor.auth.user.GetUserUseCase
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
            mUserResult.value = when (it) {
                is GetUserUseCase.Result.Success -> Result.ValidUser(it.user)
                is GetUserUseCase.Result.Failed -> {
                    Log.e("CoreViewModel", "Failed")
                    Result.InvalidUser
                }
            }
        }
    }

    fun getUserLiveData() = mUserResult

    sealed class Result {
        object Loading: Result()
        data class ValidUser(val user: UserEntity): Result()
        object InvalidUser: Result()
    }

    // endregion User ------------------------------------------------------------------------------
    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
        mGetUserUseCase.dispose()
    }

    // endregion Extra -----------------------------------------------------------------------------

}