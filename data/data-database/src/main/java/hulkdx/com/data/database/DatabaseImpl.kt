package hulkdx.com.data.database

import hulkdx.com.data.database.model.*
import hulkdx.com.data.database.model.ClothesRealmObject
import hulkdx.com.data.database.model.CurrentUserRealmObject
import hulkdx.com.data.database.model.mapClothesEntity
import hulkdx.com.data.database.model.mapClothesRealmObject
import hulkdx.com.domain.entities.ClothesEntity
import hulkdx.com.domain.entities.UserEntity
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
        user.mapCurrentUserRealmObject().execute { userRealmObject, realm ->
            realm.beginTransaction()
            realm.insertOrUpdate(userRealmObject)
            realm.commitTransaction()
        }
    }

    override fun getUser(): UserEntity? {
        var result: UserEntity? = null
        execute { _, realm ->
            val userRealmObject = realm.where(CurrentUserRealmObject::class.java).findFirst()
            result = userRealmObject?.map()
        }
        return result
    }

    // endregion UserDatabase ----------------------------------------------------------------------
    // region ClothDatabase ------------------------------------------------------------------------

    override fun saveAll(clothes: ClothesEntity) {
        mapClothesEntity(clothes).execute { clothesRealmObject, realm ->
            realm.beginTransaction()
            realm.insertOrUpdate(clothesRealmObject)
            realm.commitTransaction()
        }
    }

    override fun getAll(): ClothesEntity? {
        var result: ClothesEntity? = null
        execute { _, realm ->
            val realmObject: ClothesRealmObject =
                    realm.where(ClothesRealmObject::class.java).findFirst() ?: return@execute
            result = mapClothesRealmObject(realmObject)
        }
        return result
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