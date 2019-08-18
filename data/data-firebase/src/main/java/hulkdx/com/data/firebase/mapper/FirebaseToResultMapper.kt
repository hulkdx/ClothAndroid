package hulkdx.com.data.firebase.mapper

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import hulkdx.com.data.firebase.SaveUserInfoIntoFirebase
import hulkdx.com.domain.data.remote.RegisterEndPoint
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
internal class FirebaseToResultMapper @Inject constructor() {

    fun mapError(exception: Exception?): RegisterEndPoint.Result {
        return when (exception) {
            is FirebaseAuthWeakPasswordException -> {
                RegisterEndPoint.Result.WeakPassword
            }
            is FirebaseAuthInvalidCredentialsException -> {
                RegisterEndPoint.Result.InvalidEmailAddress
            }
            is FirebaseAuthUserCollisionException -> {
                RegisterEndPoint.Result.AccountExists
            }
            else -> {
                RegisterEndPoint.Result.GeneralError(exception)
            }
        }
    }

    fun mapSuccess(saveUserResult: SaveUserInfoIntoFirebase.Result): RegisterEndPoint.Result {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
