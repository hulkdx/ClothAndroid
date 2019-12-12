package hulkdx.com.domain.interactor.cloth.upload

import hulkdx.com.domain.repository.remote.FileUploader
import hulkdx.com.domain.di.BackgroundScheduler
import hulkdx.com.domain.di.UiScheduler
import hulkdx.com.domain.entities.CategoryEntity
import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.domain.entities.interactor.UseCaseResult
import hulkdx.com.domain.repository.local.UserDatabase
import hulkdx.com.domain.repository.remote.AddClothEndPoint
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 26/08/2019.
 */
class UploadClothUseCase @Inject constructor(
        @BackgroundScheduler private val mBackgroundScheduler: Scheduler,
        @UiScheduler         private val mUiScheduler: Scheduler,
        private val mUserDatabase: UserDatabase,
        private val mAddClothEndPoint: AddClothEndPoint,
        private val mFileUploader: FileUploader
) {

    private var mDisposable: Disposable? = null

    fun dispose() {
        mDisposable?.dispose()
    }

    fun upload(inputStream: InputStream, params: Params, callback: (UseCaseResult<ClothEntity>) -> Unit) {
        mDisposable = Single.fromCallable { uploadSync(inputStream, params) }
                .subscribeOn(mBackgroundScheduler)
                .observeOn(mUiScheduler)
                .subscribe({ data ->
                    callback(data)
                }, { throwable ->
                    callback(UseCaseResult.GeneralError(throwable))
                })
    }

    private fun uploadSync(inputStream: InputStream, params: Params): UseCaseResult<ClothEntity> {
        val user = mUserDatabase.getUser() ?: return UseCaseResult.AuthError
        val image = mFileUploader.uploadImage(inputStream)
        return mAddClothEndPoint.addCloth(user, image, params)
    }

    data class Params (
            val price: Float,
            val category: CategoryEntity,
            val currency: String
    )

}
