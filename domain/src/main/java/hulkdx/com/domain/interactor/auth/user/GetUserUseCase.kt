package hulkdx.com.domain.interactor.auth.user

import hulkdx.com.domain.di.BackgroundScheduler
import hulkdx.com.domain.di.UiScheduler
import hulkdx.com.domain.entities.CategoryEntity
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.repository.local.UserDatabase
import hulkdx.com.domain.repository.remote.CategoryEndPoint
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 22/09/2019.
 */
class GetUserUseCase @Inject constructor(
        @BackgroundScheduler private val mBackgroundScheduler: Scheduler,
        @UiScheduler private val mUiScheduler: Scheduler,
        private val mCategoryEndPoint: CategoryEndPoint,
        private val mUserDatabase: UserDatabase
) {
    private var mDisposable: Disposable? = null

    fun dispose() {
        mDisposable?.dispose()
    }

    fun fetchUserInfo(callback: (Result) -> Unit) {
        callback(Result.Loading)
        mDisposable = mUserDatabase.getUserFlowable()
                .observeOn(mBackgroundScheduler)
                .map { userEntity ->
                    val categories = mCategoryEndPoint.getAllCategories()
                        // TODO: move this logic to the server.
                        .filter {
                            return@filter it.id != "0" && it.id != "1"
                        }
                    Result.ValidUser(userEntity, categories)
                }
                .observeOn(mUiScheduler)
                .subscribe({ result ->
                    callback(result)
                }, {
                    callback(Result.InvalidUser)
                })
    }

    sealed class Result {
        object Loading: Result()
        data class ValidUser(val user: UserEntity,
                             val categories: List<CategoryEntity>): Result()
        object InvalidUser: Result()
    }
}
