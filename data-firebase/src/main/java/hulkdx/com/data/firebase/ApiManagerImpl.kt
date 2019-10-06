package hulkdx.com.data.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import hulkdx.com.data.firebase.database.ClothDatabaseFirebase
import hulkdx.com.data.firebase.database.UserDatabaseFirebase
import hulkdx.com.data.firebase.mapper.FirebaseToResultMapper
import hulkdx.com.data.firebase.util.UserNullException
import hulkdx.com.domain.repository.remote.GetClothesEndPoint
import hulkdx.com.domain.repository.remote.RegisterEndPoint
import hulkdx.com.domain.entities.ClothesEntity
import hulkdx.com.domain.entities.ImageEntity
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.entities.UserType
import hulkdx.com.domain.exception.AuthException
import hulkdx.com.domain.interactor.auth.login.LoginAuthUseCase
import hulkdx.com.domain.interactor.auth.register.RegisterAuthUseCase
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCase
import hulkdx.com.domain.repository.remote.AddClothEndPoint
import hulkdx.com.domain.repository.remote.LoginEndPoint
import java.lang.RuntimeException


/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */

internal class ApiManagerImpl(
        private val mAuth: FirebaseAuth,
        private val mFirebaseToResultMapper: FirebaseToResultMapper,
        private val mClothDatabaseFirebase: ClothDatabaseFirebase,
        private val mUserDatabase: UserDatabaseFirebase
): GetClothesEndPoint, RegisterEndPoint, AddClothEndPoint, LoginEndPoint {

    override fun register(param: RegisterAuthUseCase.Params): RegisterAuthUseCase.Result {

        val asyncToSync = AsyncToSync<RegisterAuthUseCase.Result>()

        // createUserWithEmailAndPassword is async we make it sync with locking mechanism
        mAuth.createUserWithEmailAndPassword(param.email, param.password)
                .addOnCompleteListener {
                    val result = if (it.isSuccessful) {
                        //
                        // Note: At this point userId is not available therefore the id is set to -1
                        //
                        val user = UserEntity("-1", param.email, param.firstName, param.lastName, UserType.NORMAL, null, param.gender)
                        RegisterAuthUseCase.Result.Success(user)
                    } else {
                        mFirebaseToResultMapper.mapErrorRegister(it.exception)
                    }

                    asyncToSync.signalAll(result)
                }

        val result1 = asyncToSync.await()
        if (result1 !is RegisterAuthUseCase.Result.Success) {
            return result1
        }
        val asyncToSyncTwo = AsyncToSync<Boolean>()
        val currentUser = mAuth.currentUser ?: throw UserNullException()
        mUserDatabase.register (
                param,
                currentUser,
                onComplete = DatabaseReference.CompletionListener { err, _ ->
                    val success = err == null
                    asyncToSyncTwo.signalAll(success)
                }
        )
        val result2 = asyncToSyncTwo.await()

        return if (result2) {
            val user = UserEntity(currentUser.uid, param.email, param.firstName, param.lastName, UserType.ADMIN, null, param.gender)
            RegisterAuthUseCase.Result.Success(user)
        } else {
            currentUser.delete()
            RegisterAuthUseCase.Result.GeneralError(RuntimeException("ApiManagerImpl#register: DatabaseError"))
        }
    }

    override fun getClothes(): ClothesEntity {
        return mClothDatabaseFirebase.findAll()
    }

    override fun addCloth(user: UserEntity, image: ImageEntity,
                          params: UploadClothUseCase.Params): UploadClothUseCase.Result {

        mAuth.currentUser ?: throw AuthException()

        val asyncToSync = AsyncToSync<Boolean>()

        val clothEntity = mClothDatabaseFirebase.add(image, user, params,
                onComplete = DatabaseReference.CompletionListener { err, _ ->
                    val success = err == null
                    asyncToSync.signalAll(success)
                })

        val result = asyncToSync.await()

        return if (result) {
            UploadClothUseCase.Result.Success(clothEntity)
        } else {
            UploadClothUseCase.Result.GeneralError(RuntimeException("Database Error"))
        }
    }

    override fun login(username: String, password: String): LoginAuthUseCase.Result {
        val asyncToSync = AsyncToSync<Task<AuthResult>>()

        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener {
            asyncToSync.signalAll(it)
        }

        val signInResult= asyncToSync.await()
        return if (signInResult.isSuccessful) {
            val currentUser = mAuth.currentUser ?: throw UserNullException()
            val user = mUserDatabase.login(currentUser.uid)
            LoginAuthUseCase.Result.Success(user)
        } else {
            mFirebaseToResultMapper.mapErrorLogin(signInResult.exception)
        }
    }

}
