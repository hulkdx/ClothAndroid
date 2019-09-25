package hulkdx.com.domain.interactor.auth.user

import hulkdx.com.domain.entities.UserEntity

/**
 * Created by Mohammad Jafarzadeh Rezvan on 22/09/2019.
 */
interface GetUserUseCase {

    fun fetchUserInfo(callback: (Result) -> (Unit))
    fun dispose()

    sealed class Result {
        class Success(val user: UserEntity): Result()
        class Failed(val throwable: Throwable?): Result()
    }
}