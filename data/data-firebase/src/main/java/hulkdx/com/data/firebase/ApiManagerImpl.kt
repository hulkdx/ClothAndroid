package hulkdx.com.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import hulkdx.com.data.firebase.mapper.FirebaseToResultMapper
import hulkdx.com.domain.data.remote.GetClothesEndPoint
import hulkdx.com.domain.data.remote.RegisterEndPoint
import hulkdx.com.domain.entities.ClothesEntity
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import java.lang.RuntimeException
import javax.inject.Inject


/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */

internal class ApiManagerImpl @Inject constructor(
        private val mAuth: FirebaseAuth,
        private val mFirebaseToResultMapper: FirebaseToResultMapper,
        private val mSaveUserInfoIntoFirebase: SaveUserInfoIntoFirebase
): GetClothesEndPoint, RegisterEndPoint {

    override fun register(param: RegisterAuthUseCase.Params): RegisterAuthUseCase.Result {

        val asyncToSync = AsyncToSync<RegisterAuthUseCase.Result>()

        // createUserWithEmailAndPassword is async we make it sync with locking mechanism
        mAuth.createUserWithEmailAndPassword(param.email, param.password)
                .addOnCompleteListener {
                    val result = if (it.isSuccessful) {
                        RegisterAuthUseCase.Result.Success()
                    } else {
                        mFirebaseToResultMapper.mapError(it.exception)
                    }

                    asyncToSync.signalAll(result)
                }

        val result1 = asyncToSync.await()
        if (result1 !is RegisterAuthUseCase.Result.Success) {
            return result1
        }
        val asyncToSyncTwo = AsyncToSync<Boolean>()
        val currentUser = mAuth.currentUser
        mSaveUserInfoIntoFirebase.saveUserInfo (
                param,
                currentUser,
                onComplete = DatabaseReference.CompletionListener { err, _ ->
                    val success = err == null
                    asyncToSyncTwo.signalAll(success)
                }
        )
        val result2 = asyncToSyncTwo.await()

        return if (result2) {
            result1
        } else {
            currentUser?.delete()
            RegisterAuthUseCase.Result.GeneralError(RuntimeException("DatabaseError"))
        }
    }

    override fun getClothes(): ClothesEntity {
        TODO()
    }

}
