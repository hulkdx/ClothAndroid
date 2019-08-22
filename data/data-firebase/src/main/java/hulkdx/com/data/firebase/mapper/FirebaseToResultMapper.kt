package hulkdx.com.data.firebase.mapper

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import hulkdx.com.data.firebase.SaveUserInfoIntoFirebase
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
internal class FirebaseToResultMapper @Inject constructor() {

    fun mapError(exception: Exception?): RegisterAuthUseCase.Result {
        return when (exception) {
            is FirebaseAuthWeakPasswordException -> {
                RegisterAuthUseCase.Result.WeakPassword
            }
            is FirebaseAuthInvalidCredentialsException -> {
                RegisterAuthUseCase.Result.InvalidEmailAddress
            }
            is FirebaseAuthUserCollisionException -> {
                RegisterAuthUseCase.Result.AccountExists
            }
            else -> {
                RegisterAuthUseCase.Result.GeneralError(exception)
            }
        }
    }

}
