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

    fun mapSuccess(isSaveUserSuccess: Boolean): RegisterAuthUseCase.Result {
        return if (isSaveUserSuccess) {
            RegisterAuthUseCase.Result.Success()
        } else {
            // TODO: what should happens if the user is added to firebase auth but not database?
            // TODO: it needs to be atomic and remove the firebase auth
            throw RuntimeException("NOT IMPLEMENTED")
        }
    }

}
