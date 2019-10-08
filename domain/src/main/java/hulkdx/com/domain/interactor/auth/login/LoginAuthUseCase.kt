package hulkdx.com.domain.interactor.auth.login

import hulkdx.com.domain.di.BackgroundScheduler
import hulkdx.com.domain.di.UiScheduler
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.repository.local.UserDatabase
import hulkdx.com.domain.repository.remote.LoginEndPoint
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */
class LoginAuthUseCase @Inject constructor(
        @BackgroundScheduler private val mBackgroundScheduler: Scheduler,
        @UiScheduler         private val mUiScheduler: Scheduler,
        private val mUserDatabase: UserDatabase,
        private val mLoginEndPoint: LoginEndPoint
) {

    var mDisposable: Disposable? = null

    fun dispose() {
        mDisposable?.dispose()
    }

    fun login(username: String, password: String, callback: (Result) -> Unit) {
        mDisposable = Single.fromCallable{ loginSync(username, password) }
                .subscribeOn(mBackgroundScheduler)
                .observeOn(mUiScheduler)
                .subscribe({
                    callback(it)
                }, {
                    callback(Result.GeneralError(it))
                })
    }

    private fun loginSync(username: String, password: String): Result {
        val result = mLoginEndPoint.login(username, password)

        if (result is Result.Success) {
            mUserDatabase.saveUser(result.user)
        }

        return result
    }

    sealed class Result {
        object Loading: Result()
        data class Success(val user: UserEntity): Result()
        object WrongEmail: Result()
        object WrongPassword: Result()
        data class GeneralError(val throwable: Throwable?): Result()
    }
}
