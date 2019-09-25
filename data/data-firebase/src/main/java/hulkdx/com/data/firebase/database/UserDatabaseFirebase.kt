package hulkdx.com.data.firebase.database

import com.google.firebase.auth.FirebaseUser
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import java.lang.Exception
import javax.inject.Inject
import com.google.firebase.database.DatabaseReference
import hulkdx.com.data.firebase.model.api.UserApiModel
import hulkdx.com.domain.entities.UserGender


/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
internal class UserDatabaseFirebase(
        private val mUserDatabase: DatabaseReference
) {

    @Throws(UserNullException::class)
    fun saveUserInfo(params: RegisterAuthUseCase.Params, user: FirebaseUser,
                     onComplete: DatabaseReference.CompletionListener) {
        val userApi = convertParamsToUserApi(params, user)
        mUserDatabase.child(userApi.userUuid).setValue(userApi, onComplete)
    }

    private fun convertParamsToUserApi(params: RegisterAuthUseCase.Params,
                                       user: FirebaseUser): UserApiModel {
        return UserApiModel(
                firstName = params.firstName,
                lastName = params.lastName,
                email = params.email,
                gender = UserGender.convert(params.gender),
                userUuid = user.uid
        )
    }

    class UserNullException: Exception("SaveUserInfoIntoFirebase: User is null")
}
