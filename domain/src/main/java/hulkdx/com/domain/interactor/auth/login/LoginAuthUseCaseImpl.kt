package hulkdx.com.domain.interactor.auth.login

import hulkdx.com.domain.di.BackgroundScheduler
import hulkdx.com.domain.di.UiScheduler
import hulkdx.com.domain.repository.local.UserDatabase
import hulkdx.com.domain.repository.remote.LoginEndPoint
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */
class LoginAuthUseCaseImpl @Inject constructor(
        @BackgroundScheduler private val mBackgroundScheduler: Scheduler,
        @UiScheduler         private val mUiScheduler: Scheduler,
        private val mUserDatabase: UserDatabase,
        private val mLoginEndPoint: LoginEndPoint
): LoginAuthUseCase {

    var mDisposable: Disposable? = null

    override fun login(username: String, password: String, callback: (LoginAuthUseCase.Result) -> Unit) {
        mDisposable = Single.fromCallable{ loginSync(username, password) }
                .subscribeOn(mBackgroundScheduler)
                .observeOn(mUiScheduler)
                .subscribe({
                    callback(it)
                }, {
                    callback(LoginAuthUseCase.Result.GeneralError(it))
                })
    }

    private fun loginSync(username: String, password: String): LoginAuthUseCase.Result {
        val result = mLoginEndPoint.login(username, password)

        if (result is LoginAuthUseCase.Result.Success) {
            mUserDatabase.saveUser(result.user)
        }

        return result
    }

    override fun dispose() {
        mDisposable?.dispose()
    }
}
