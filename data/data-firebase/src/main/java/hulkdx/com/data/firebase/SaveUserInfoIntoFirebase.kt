package hulkdx.com.data.firebase

import com.google.firebase.auth.FirebaseUser
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
internal class SaveUserInfoIntoFirebase @Inject constructor() {

    @Throws(UserNullException::class)
    fun saveUserInfoIntoFirebase(params: RegisterAuthUseCase.Params, user: FirebaseUser?): Result {
        if (user == null) {
            throw UserNullException()
        }
        TODO()
    }

    enum class Result {
        Success
    }

    class UserNullException: Exception("SaveUserInfoIntoFirebase: User is null")

}