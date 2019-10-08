package hulkdx.com.domain.interactor.auth.register

import hulkdx.com.domain.repository.remote.RegisterEndPoint
import hulkdx.com.domain.di.BackgroundScheduler
import hulkdx.com.domain.di.UiScheduler
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.entities.UserGender
import hulkdx.com.domain.repository.local.UserDatabase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */
class RegisterAuthUseCase @Inject constructor(
        @BackgroundScheduler private val mBackgroundScheduler: Scheduler,
        @UiScheduler         private val mUiScheduler: Scheduler,
        private val mUserDatabase: UserDatabase,
        private val mRegisterEndPoint: RegisterEndPoint
) {

    private var mDisposable: Disposable? = null

    fun dispose() {
        mDisposable?.dispose()
    }

    fun register(param: Params,
                          callback: (Result) -> Unit) {

        mDisposable = Single.fromCallable { registerSync(param) }
                .subscribeOn(mBackgroundScheduler)
                .observeOn(mUiScheduler)
                .subscribe({
                    callback(it)
                }, { throwable ->
                    callback(Result.GeneralError(throwable))
                })
    }

    private fun registerSync(param: Params): Result {
        val result = mRegisterEndPoint.register(param)
        if (result is Result.Success) {
            mUserDatabase.saveUser(result.user)
        }
        return result
    }

    data class Params (
            val email: String,
            val password: String,
            val firstName: String,
            val lastName: String,
            val gender: UserGender
    )

    sealed class Result {
        data class Success(val user: UserEntity): Result()
        object InvalidEmailAddress: Result()
        object WeakPassword: Result()
        object AccountExists: Result()
        data class GeneralError(val throwable: Throwable?): Result()
    }
}
