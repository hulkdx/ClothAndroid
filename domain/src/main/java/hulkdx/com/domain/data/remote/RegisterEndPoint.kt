package hulkdx.com.domain.data.remote

import hulkdx.com.domain.entities.ImageEntity

/**
 * Created by Mohammad Jafarzadeh Rezvan on 17/08/2019.
 */
interface RegisterEndPoint {

    fun register(email: String,
                 password: String,
                 firstName: String,
                 lastName: String,
                 userImage: ImageEntity? = null): Result

    sealed class Result {
        class Success(
//                val mData: UserEntity
        ): Result()
        object InvalidEmailAddress: Result()
        object WeakPassword: Result()
        object AccountExists: Result()
        data class GeneralError(val throwable: Throwable?): Result()
    }
}