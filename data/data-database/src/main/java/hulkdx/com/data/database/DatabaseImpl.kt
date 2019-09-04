package hulkdx.com.data.database

import hulkdx.com.data.database.model.UserRealmObject
import hulkdx.com.domain.entities.ClothesEntity
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.entities.UserGender
import hulkdx.com.domain.repository.local.ClothDatabase
import hulkdx.com.domain.repository.local.UserDatabase
import io.realm.Realm
import io.realm.RealmConfiguration
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 31/08/2019.
 */
class DatabaseImpl @Inject constructor(
        private val mRealmConfiguration: RealmConfiguration
): UserDatabase, ClothDatabase {

    private val mLock = ReentrantLock()

    // region UserDatabase -------------------------------------------------------------------------

    override fun saveUser(user: UserEntity) {
        UserRealmObject.map(user).execute { userRealmObject, realm ->
            realm.beginTransaction()
            realm.insertOrUpdate(userRealmObject)
            realm.commitTransaction()
        }
    }

    override fun getUser(): UserEntity? {
        var result: UserEntity? = null
        execute { _, realm ->
            val userRealmObject = realm.where(UserRealmObject::class.java).findFirst()
            result = userRealmObject?.map()
        }
        return result
    }

    // endregion UserDatabase ----------------------------------------------------------------------
    // region ClothDatabase ------------------------------------------------------------------------

    override fun saveAll(clothes: ClothesEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): ClothesEntity? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // endregion ClothDatabase ---------------------------------------------------------------------
    // region Extra --------------------------------------------------------------------------------

    private fun getRealm(): Realm {
        return Realm.getInstance(mRealmConfiguration)
    }

    private inline fun <T> T.execute(execution: (T, Realm) -> (Unit)) {
        var realm: Realm? = null
        try {
            mLock.lock()
            realm = getRealm()
            execution(this, realm)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            realm?.close()
            mLock.unlock()
        }
    }

    // endregion Extra -----------------------------------------------------------------------------
}