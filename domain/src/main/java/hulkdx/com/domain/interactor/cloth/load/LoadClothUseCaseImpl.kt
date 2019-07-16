package hulkdx.com.domain.interactor.cloth.load

import hulkdx.com.domain.di.BackgroundScheduler
import hulkdx.com.domain.di.UiScheduler
import hulkdx.com.domain.model.Cloth
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */
class LoadClothUseCaseImpl @Inject constructor(
        @BackgroundScheduler private val mBackgroundScheduler: Scheduler,
        @UiScheduler         private val mUiScheduler: Scheduler
): LoadClothUseCase {

    private var mDisposable: Disposable? = null

    override fun loadAsync(onComplete: (UseCaseResult<List<Cloth>>) -> Unit) {

        Single.fromCallable { loadSync() }
                .subscribeOn(mBackgroundScheduler)
                .observeOn(mUiScheduler)
                .subscribe(object: SingleObserver<List<Cloth>> {

                    override fun onSuccess(clothes: List<Cloth>) {
                        //onComplete(UseCaseResult(status = STATUS_SUCCESS, data = clothes))
                    }

                    override fun onError(throwable: Throwable) {
                        when (throwable) {
                            is IOException -> {
                                // Network Errors
                                //onComplete(UseCaseResult(status = STATUS_NETWORK_ERROR))
                            }
                            else -> {
                                // General Errors
                                //onComplete(UseCaseResult(status = STATUS_GENERAL_ERROR))
                            }
                        }
                    }

                    override fun onSubscribe(disposable: Disposable) {
                        mDisposable = disposable
                    }

                })
    }

    @Throws(IOException::class)
    private fun loadSync(): List<Cloth> {
        return mLoadClothEndPoint.execute()
    }

    override fun dispose() {
        mDisposable?.dispose()
    }

}
