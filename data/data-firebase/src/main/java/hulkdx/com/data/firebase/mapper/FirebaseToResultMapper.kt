package hulkdx.com.data.firebase.mapper

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import hulkdx.com.domain.interactor.auth.login.LoginAuthUseCase
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
internal class FirebaseToResultMapper @Inject constructor() {

    fun mapErrorRegister(exception: Exception?): RegisterAuthUseCase.Result {
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

    fun mapErrorLogin(exception: Exception?): LoginAuthUseCase.Result {
        return when (exception) {
            is FirebaseAuthInvalidUserException -> LoginAuthUseCase.Result.WrongEmail
            is FirebaseAuthInvalidCredentialsException -> LoginAuthUseCase.Result.WrongPassword
            else -> {
                LoginAuthUseCase.Result.GeneralError(exception)
            }
        }
    }

}
