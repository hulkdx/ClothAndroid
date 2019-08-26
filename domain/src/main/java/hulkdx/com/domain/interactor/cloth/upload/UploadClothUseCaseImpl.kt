package hulkdx.com.domain.interactor.cloth.upload

import hulkdx.com.domain.data.remote.FileUploader
import hulkdx.com.domain.di.BackgroundScheduler
import hulkdx.com.domain.di.UiScheduler
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCase.Result
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 26/08/2019.
 */
class UploadClothUseCaseImpl @Inject constructor(
        @BackgroundScheduler private val mBackgroundScheduler: Scheduler,
        @UiScheduler         private val mUiScheduler: Scheduler,
        private val mFileUploader: FileUploader
): UploadClothUseCase {

    private var mDisposable: Disposable? = null

    override fun upload(inputStream: InputStream, callback: (Result) -> Unit) {
        mDisposable = Single.fromCallable { mFileUploader.upload(inputStream) }
                .subscribeOn(mBackgroundScheduler)
                .observeOn(mUiScheduler)
                .subscribe({ data ->
                }, { throwable ->
                })
    }

    override fun dispose() {
        mDisposable?.dispose()
    }

}
