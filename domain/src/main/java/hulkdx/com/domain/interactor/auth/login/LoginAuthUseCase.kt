package hulkdx.com.domain.interactor.auth.login

import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.entities.UserGender


/**
 * Created by Mohammad Jafarzadeh Rezvan on 15/07/2019.
 */
interface LoginAuthUseCase {

    fun login(username: String, password: String, callback: (Result) -> (Unit))

    fun dispose()

    sealed class Result {
        data class Success(val user: UserEntity): Result()
        object WrongEmail: Result()
        object WrongPassword: Result()
        data class GeneralError(val throwable: Throwable?): Result()
    }
}
