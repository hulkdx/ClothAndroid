package hulkdx.com.domain.interactor.auth.register

import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.entities.UserGender


/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */
interface RegisterAuthUseCase {

    fun register(param: Params, callback: (Result) -> (Unit))

    fun dispose()

    data class Params (
            val email: String,
            val password: String,
            val firstName: String,
            val lastName: String,
            val gender: UserGender
    )

    sealed class Result {
        class Success(val user: UserEntity): Result()
        object InvalidEmailAddress: Result()
        object WeakPassword: Result()
        object AccountExists: Result()
        data class GeneralError(val throwable: Throwable?): Result()
    }
}
