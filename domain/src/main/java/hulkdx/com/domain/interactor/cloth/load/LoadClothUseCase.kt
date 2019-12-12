package hulkdx.com.domain.interactor.cloth.load

import hulkdx.com.domain.repository.remote.GetClothesEndPoint
import hulkdx.com.domain.di.BackgroundScheduler
import hulkdx.com.domain.di.UiScheduler
import hulkdx.com.domain.exception.AuthException
import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.domain.entities.interactor.UseCaseResult
import hulkdx.com.domain.repository.local.ClothDatabase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */
class LoadClothUseCase @Inject constructor(
        @BackgroundScheduler private val mBackgroundScheduler: Scheduler,
        @UiScheduler         private val mUiScheduler: Scheduler,
        private val mClothDatabase: ClothDatabase,
        private val mClothApiManager: GetClothesEndPoint
) {

    private var mDisposable: Disposable? = null

    fun dispose() {
        mDisposable?.dispose()
    }

    fun loadAsync(onSuccess:      (List<ClothEntity>) -> Unit,
                  onGeneralError: (Throwable) -> Unit,
                  onNetworkError: (Throwable) -> Unit,
                  onAuthError:    (Throwable) -> Unit) {

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

    fun loadSingleRandomClothAsync(callback: (UseCaseResult<ClothEntity>) -> Unit) {

    }

    private fun loadSync(): List<ClothEntity> {
        val clothesListEntity = mClothApiManager.getClothes()
        mClothDatabase.saveAll(clothesListEntity)
        return clothesListEntity.clothes
    }

}
