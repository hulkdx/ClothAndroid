package hulkdx.com.data.firebase

import com.google.firebase.auth.FirebaseAuth
import hulkdx.com.domain.data.remote.ApiManager
import hulkdx.com.domain.data.remote.RegisterResult
import hulkdx.com.domain.entities.ClothesEntity
import hulkdx.com.domain.entities.ImageEntity
import hulkdx.com.domain.entities.UserEntity
import java.lang.RuntimeException
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject


/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */

internal class ApiManagerImpl @Inject constructor(
        private val mAuth: FirebaseAuth
): ApiManager {

    private val mLock = ReentrantLock()
    private val mLockCondition = mLock.newCondition()

//    override fun login(): UserEntity {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    override fun register(email: String,
                          password: String,
                          firstName: String,
                          lastName: String,
                          userImage: ImageEntity?): RegisterResult {
        var result: RegisterResult? = null
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
            } else {
            }
            mLock.lock()
            mLockCondition.signalAll()
            mLock.unlock()
        }
        mLock.lock()
        mLockCondition.await()
        mLock.unlock()
        if (result == null) {
            // This should never happens!
            throw RuntimeException("result == null")
        }
        return result
    }

    override fun getClothes(): ClothesEntity {
        TODO()
    }

}
