package hulkdx.com.domain.interactor.cloth.load

import hulkdx.com.domain.data.remote.ClothApiManager
import hulkdx.com.domain.di.BackgroundScheduler
import hulkdx.com.domain.di.UiScheduler
import hulkdx.com.domain.exception.AuthException
import hulkdx.com.domain.model.Cloth
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */
class LoadClothUseCaseImpl @Inject constructor(
        @BackgroundScheduler private val mBackgroundScheduler: Scheduler,
        @UiScheduler         private val mUiScheduler: Scheduler,
        private val mClothApiManager: ClothApiManager
): LoadClothUseCase {

    private var mDisposable: Disposable? = null

    override fun loadAsync(onSuccess:      (List<Cloth>) -> Unit,
                           onGeneralError: (Throwable)   -> Unit,
                           onNetworkError: (Throwable)   -> Unit,
                           onAuthError:    (Throwable)   -> Unit) {

        mDisposable = Single.fromCallable { loadSync() }
                .subscribeOn(mBackgroundScheduler)
                .observeOn(mUiScheduler)
                .subscribe({
                    onSuccess(it)
                }, { throwable ->
                    when (throwable) {
                        is IOException -> {
                            onNetworkError(throwable)
                        }
                        is AuthException -> {
                            onAuthError(throwable)
                        }
                        else -> {
                            onGeneralError(throwable)
                        }
                    }
                })
    }

    private fun loadSync(): List<Cloth> {
        return mClothApiManager.getCloths()
    }

    override fun dispose() {
        mDisposable?.dispose()
    }

}
