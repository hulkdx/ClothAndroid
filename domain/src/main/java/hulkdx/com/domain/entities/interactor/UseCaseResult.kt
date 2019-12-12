package hulkdx.com.domain.entities.interactor

/**
 * Created by Mohammad Jafarzadeh Rezvan on 08/12/2019.
 */

sealed class UseCaseResult<out T> {

    data class Success<T>(val data: T): UseCaseResult<T>()
    object AuthError: UseCaseResult<Nothing>()
    object NetworkError: UseCaseResult<Nothing>()
    class GeneralError(val throwable: Throwable? = null): UseCaseResult<Nothing>()

    inline fun <V> map(mapper: (T) -> V): UseCaseResult<V> {
        return when (this) {
            is Success      -> Success(mapper.invoke(data))
            is AuthError    -> AuthError
            is NetworkError -> NetworkError
            is GeneralError -> GeneralError(throwable)
        }
    }
}

