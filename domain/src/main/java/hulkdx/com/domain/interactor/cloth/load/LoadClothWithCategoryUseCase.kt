package hulkdx.com.domain.interactor.cloth.load

import hulkdx.com.domain.repository.remote.GetClothesEndPoint
import hulkdx.com.domain.di.BackgroundScheduler
import hulkdx.com.domain.di.UiScheduler
import hulkdx.com.domain.entities.Category
import hulkdx.com.domain.exception.AuthException
import hulkdx.com.domain.entities.ClothWithCategoryEntity
import hulkdx.com.domain.entities.interactor.UseCaseResult
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */

class LoadClothWithCategoryUseCase @Inject constructor(
        @BackgroundScheduler private val mBackgroundScheduler: Scheduler,
        @UiScheduler         private val mUiScheduler: Scheduler,
        private val mGetClothesEndPoint: GetClothesEndPoint
) {

    private var mDisposable: Disposable? = null

    fun dispose() {
        mDisposable?.dispose()
    }

    fun loadAsync(callback: (UseCaseResult<List<Category>>) -> Unit) {

        mDisposable = Single.fromCallable { loadSync() }
                .subscribeOn(mBackgroundScheduler)
                .observeOn(mUiScheduler)
                .subscribe({
                     callback(UseCaseResult.Success(it))
                }, { throwable ->
                    when (throwable) {
                        is IOException -> {
                            callback(UseCaseResult.NetworkError)
                        }
                        is AuthException -> {
                            callback(UseCaseResult.AuthError)
                        }
                        else -> {
                            callback(UseCaseResult.GeneralError(throwable))
                        }
                    }
                })
    }

    private fun loadSync() = mGetClothesEndPoint.getCategory()

}
