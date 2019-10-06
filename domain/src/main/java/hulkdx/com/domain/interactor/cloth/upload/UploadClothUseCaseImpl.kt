package hulkdx.com.domain.interactor.cloth.upload

import hulkdx.com.domain.repository.remote.FileUploader
import hulkdx.com.domain.di.BackgroundScheduler
import hulkdx.com.domain.di.UiScheduler
import hulkdx.com.domain.interactor.auth.user.GetUserUseCase
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCase.Params
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCase.Result
import hulkdx.com.domain.repository.local.UserDatabase
import hulkdx.com.domain.repository.remote.AddClothEndPoint
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.io.InputStream
import java.lang.RuntimeException
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 26/08/2019.
 */
class UploadClothUseCaseImpl @Inject constructor(
        @BackgroundScheduler private val mBackgroundScheduler: Scheduler,
        @UiScheduler         private val mUiScheduler: Scheduler,
        private val mUserDatabase: UserDatabase,
        private val mAddClothEndPoint: AddClothEndPoint,
        private val mFileUploader: FileUploader
): UploadClothUseCase {

    private var mDisposable: Disposable? = null

    override fun upload(inputStream: InputStream, params: Params, callback: (Result) -> Unit) {

        mDisposable = Single.fromCallable { uploadSync(inputStream, params) }
                .subscribeOn(mBackgroundScheduler)
                .observeOn(mUiScheduler)
                .subscribe({ data ->
                    callback(data)
                }, { throwable ->
                    callback(Result.GeneralError(throwable))
                })
    }

    private fun uploadSync(inputStream: InputStream, params: Params): Result {
        val user = mUserDatabase.getUser() ?: return Result.AuthError
        val image = mFileUploader.uploadImage(inputStream)
        return mAddClothEndPoint.addCloth(user, image, params)
    }

    override fun dispose() {
        mDisposable?.dispose()
    }

}
