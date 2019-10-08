package hulkdx.com.domain.interactor.auth.user

import hulkdx.com.domain.di.BackgroundScheduler
import hulkdx.com.domain.di.UiScheduler
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.repository.local.UserDatabase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 22/09/2019.
 */
class GetUserUseCase @Inject constructor(
        @BackgroundScheduler private val mBackgroundScheduler: Scheduler,
        @UiScheduler private val mUiScheduler: Scheduler,
        private val mUserDatabase: UserDatabase
) {
    private var mDisposable: Disposable? = null

    fun dispose() {
        mDisposable?.dispose()
    }

    fun fetchUserInfo(callback: (GetUserUseCase.Result) -> Unit) {
        callback(GetUserUseCase.Result.Loading)
        mDisposable = mUserDatabase.getUserFlowable()
                .observeOn(mUiScheduler)
                .subscribe({ result ->
                    callback(result)
                }, {
                    callback(GetUserUseCase.Result.InvalidUser)
                })
    }

    sealed class Result {
        object Loading: Result()
        data class ValidUser(val user: UserEntity): Result()
        object InvalidUser: Result()
    }
}
