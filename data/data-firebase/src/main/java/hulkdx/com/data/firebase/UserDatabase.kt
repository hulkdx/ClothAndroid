package hulkdx.com.data.firebase

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseError
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import java.lang.Exception
import javax.inject.Inject
import com.google.firebase.database.DatabaseReference
import hulkdx.com.data.firebase.model.api.UserApiModel
import hulkdx.com.domain.entities.UserGender
import javax.inject.Named


/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
class SaveUserInfoIntoFirebase @Inject constructor(
        @Named("USER") private val mUserDatabase: DatabaseReference
) {

    @Throws(UserNullException::class)
    fun saveUserInfo(params: RegisterAuthUseCase.Params, user: FirebaseUser?,
                     onComplete: DatabaseReference.CompletionListener) {
        if (user == null) {
            throw UserNullException()
        }
        val userApi = convertParamsToUserApi(params, user)
        mUserDatabase.child(userApi.userUuid).setValue(userApi, onComplete)
    }

    private fun convertParamsToUserApi(params: RegisterAuthUseCase.Params,
                                       user: FirebaseUser): UserApiModel {
        return UserApiModel (
                firstName = params.firstName,
                lastName = params.lastName,
                email = params.email,
                gender = UserGender.convert(params.gender),
                userUuid = user.uid
        )
    }

    class UserNullException: Exception("SaveUserInfoIntoFirebase: User is null")
}
