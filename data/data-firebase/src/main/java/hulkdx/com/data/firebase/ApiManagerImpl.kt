package hulkdx.com.data.firebase

import com.google.firebase.auth.FirebaseAuth
import hulkdx.com.data.firebase.mapper.FirebaseToResultMapper
import hulkdx.com.domain.data.remote.GetClothesEndPoint
import hulkdx.com.domain.data.remote.RegisterEndPoint
import hulkdx.com.domain.entities.ClothesEntity
import hulkdx.com.domain.entities.ImageEntity
import javax.inject.Inject


/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */

internal class ApiManagerImpl @Inject constructor(
        private val mAuth: FirebaseAuth,
        private val mFirebaseToResultMapper: FirebaseToResultMapper,
        private val mSaveUserInfoIntoFirebase: SaveUserInfoIntoFirebase
): GetClothesEndPoint, RegisterEndPoint {

//    override fun login(): UserEntity {
//    }

    override fun register(email: String,
                          password: String,
                          firstName: String,
                          lastName: String,
                          userImage: ImageEntity?): RegisterEndPoint.Result {

        val asyncToSync = AsyncToSync<RegisterEndPoint.Result>()

        // createUserWithEmailAndPassword is async we make it sync with locking mechanism
        // TODO this and mSaveUserInfoIntoFirebase.start needs to be atomic
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    val result = if (it.isSuccessful) {
                        val saveUserResult = mSaveUserInfoIntoFirebase.start(firstName, lastName)
                        mFirebaseToResultMapper.mapSuccess(
                                saveUserResult
                        )
                    } else {
                        mFirebaseToResultMapper.mapError(
                                it.exception
                        )
                    }

                    asyncToSync.signalAll(result)
                }

        return asyncToSync.await()
    }

    override fun getClothes(): ClothesEntity {
        TODO()
    }

}
