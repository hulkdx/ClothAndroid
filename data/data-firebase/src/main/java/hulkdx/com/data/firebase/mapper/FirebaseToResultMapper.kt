package hulkdx.com.data.firebase.mapper

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import hulkdx.com.data.firebase.SaveUserInfoIntoFirebase
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase.Result
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
internal class FirebaseToResultMapper @Inject constructor() {

    fun mapError(exception: Exception?): Result {
        return when (exception) {
            is FirebaseAuthWeakPasswordException -> {
                Result.WeakPassword
            }
            is FirebaseAuthInvalidCredentialsException -> {
                Result.InvalidEmailAddress
            }
            is FirebaseAuthUserCollisionException -> {
                Result.AccountExists
            }
            else -> {
                Result.GeneralError(exception)
            }
        }
    }

    fun mapSuccess(saveUserResult: SaveUserInfoIntoFirebase.Result): Result {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
