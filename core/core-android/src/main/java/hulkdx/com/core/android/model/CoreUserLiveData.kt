package hulkdx.com.core.android.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.interactor.auth.user.GetUserUseCase
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Mohammad Jafarzadeh Rezvan on 22/09/2019.
 */
@Singleton
class CoreUserLiveData @Inject constructor() {

    private val mLogTag = "CoreUserLiveData"
    private val mResult = MutableLiveData<Result>(Result.Loading)

    fun result(useCaseResult: GetUserUseCase.Result) {
        mResult.value = when (useCaseResult) {
            is GetUserUseCase.Result.Success -> Result.ValidUser(useCaseResult.user)
            is GetUserUseCase.Result.Failed -> {
                Log.e(mLogTag, "GetUserUseCase Failed", useCaseResult.throwable)
                Result.InvalidUser
            }
        }
    }

    fun logout() {
        mResult.value = Result.InvalidUser
    }

    fun getLiveData(): LiveData<Result> = mResult

    sealed class Result {
        object Loading: Result()
        data class ValidUser(val user: UserEntity): Result()
        object InvalidUser: Result()
    }
}