package hulkdx.com.data.firebase.database

import com.google.firebase.auth.FirebaseUser
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import java.lang.Exception
import com.google.firebase.database.DatabaseReference
import hulkdx.com.data.firebase.mapper.ApiModelMapper
import hulkdx.com.data.firebase.util.convertToModel
import hulkdx.com.domain.entities.UserEntity


/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
internal class UserDatabaseFirebase(
        private val mUserDatabase: DatabaseReference,
        private val mApiModelMapper: ApiModelMapper
) {

    fun register(params: RegisterAuthUseCase.Params, user: FirebaseUser,
                 onComplete: DatabaseReference.CompletionListener) {
        val userApi = UserEntity(
                firstName = params.firstName,
                lastName = params.lastName,
                emailAddress = params.email,
                gender = params.gender,
                id = user.uid,
                image = null
        )
        mUserDatabase.child(userApi.id).setValue(userApi, onComplete)
    }

    fun login(uid: String): UserEntity {
        return mUserDatabase.child(uid).convertToModel { value ->
            mApiModelMapper.mapUserHashToEntity(value)
        }
    }
}
