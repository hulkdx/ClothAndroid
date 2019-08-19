package hulkdx.com.domain.interactor.auth.register

import hulkdx.com.domain.data.remote.RegisterEndPoint
import hulkdx.com.domain.di.BackgroundScheduler
import hulkdx.com.domain.di.UiScheduler
import hulkdx.com.domain.entities.ClothEntity
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */
class RegisterAuthUseCaseImpl @Inject constructor(
        @BackgroundScheduler private val mBackgroundScheduler: Scheduler,
        @UiScheduler         private val mUiScheduler: Scheduler,
        private val mRegisterEndPoint: RegisterEndPoint
): RegisterAuthUseCase {

    private var mDisposable: Disposable? = null

    override fun register(param: RegisterAuthUseCase.Params,
                          callback: (RegisterAuthUseCase.Result) -> Unit) {

        mDisposable = Single.fromCallable { registerSync(param) }
                .subscribeOn(mBackgroundScheduler)
                .observeOn(mUiScheduler)
                .subscribe({
                    callback(it)
                }, { throwable ->
                    callback(RegisterAuthUseCase.Result.GeneralError(throwable))
                })
    }

    private fun registerSync(param: RegisterAuthUseCase.Params): RegisterAuthUseCase.Result {
        return mRegisterEndPoint.register(param)
    }

    override fun dispose() {
        mDisposable?.dispose()
    }

}