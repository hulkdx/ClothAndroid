package hulkdx.com.domain.interactor.auth.user

import hulkdx.com.domain.di.BackgroundScheduler
import hulkdx.com.domain.di.UiScheduler
import hulkdx.com.domain.repository.local.UserDatabase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 22/09/2019.
 */
class GetUserUseCaseImpl @Inject constructor(
        @BackgroundScheduler private val mBackgroundScheduler: Scheduler,
        @UiScheduler private val mUiScheduler: Scheduler,
        private val mUserDatabase: UserDatabase
): GetUserUseCase {
    private var mDisposable: Disposable? = null

    override fun fetchUserInfo(callback: (GetUserUseCase.Result) -> Unit) {
        mDisposable = Single.fromCallable { mUserDatabase.getUser() }
                .subscribeOn(mBackgroundScheduler)
                .observeOn(mUiScheduler)
                .subscribe({
                    if (it != null) callback(GetUserUseCase.Result.Success(it))
                    else callback(GetUserUseCase.Result.Failed(null))
                }, {
                    callback(GetUserUseCase.Result.Failed(it))
                })
    }

    override fun dispose() {
        mDisposable?.dispose()
    }

}