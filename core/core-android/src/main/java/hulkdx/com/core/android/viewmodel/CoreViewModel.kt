package hulkdx.com.core.android.viewmodel

import androidx.lifecycle.ViewModel
import hulkdx.com.core.android.model.CoreUserLiveData
import hulkdx.com.domain.interactor.auth.user.GetUserUseCase
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/07/2019.
 *
 * This class is not singleton. Add a singleton dependency if it needs one.
 */
class CoreViewModel @Inject constructor(
        private val mGetUserUseCase: GetUserUseCase,
        private val mCoreUserLiveData: CoreUserLiveData
): ViewModel() {

    // region User ---------------------------------------------------------------------------------

    fun logout() {
        mCoreUserLiveData.logout()
    }

    fun fetchUserInfo() {
        mGetUserUseCase.fetchUserInfo {
            mCoreUserLiveData.result(it)
        }
    }

    fun getUserLiveData() = mCoreUserLiveData.getLiveData()

    // endregion User ------------------------------------------------------------------------------
    // region Extra --------------------------------------------------------------------------------

    override fun onCleared() {
        super.onCleared()
        mGetUserUseCase.dispose()
    }

    // endregion Extra -----------------------------------------------------------------------------

}