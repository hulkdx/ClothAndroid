package hulkdx.com.data.firebase.database

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import javax.inject.Inject
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import hulkdx.com.data.firebase.AsyncToSync
import hulkdx.com.data.firebase.mapper.ApiModelMapper
import hulkdx.com.data.firebase.model.ReadDatabaseResult
import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.domain.entities.ClothesEntity
import hulkdx.com.domain.entities.ImageEntity
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCase
import java.lang.RuntimeException
import javax.inject.Named


/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
internal class ClothDatabaseFirebase(
        private val mClothDatabase: DatabaseReference,
        private val mApiModelMapper: ApiModelMapper
) {

    fun add(image: ImageEntity,
            user: UserEntity,
            params: UploadClothUseCase.Params,
            onComplete: DatabaseReference.CompletionListener): ClothEntity {
        val id = mClothDatabase.push().key ?: throw RuntimeException("id == null")
        val clothEntity = ClothEntity(
                id = id,
                image = image,
                user = user,
                price = params.price,
                currency = params.currency
        )
        val clothApiModel = mApiModelMapper.mapClothToApi(clothEntity)
        mClothDatabase.child(id).setValue(clothApiModel, onComplete)
        return clothEntity
    }

    fun findAll(): ClothesEntity {
        val asyncToSync = AsyncToSync<ReadDatabaseResult>()

        mClothDatabase.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                asyncToSync.signalAll(ReadDatabaseResult.Error(databaseError))
            }

            override fun onDataChange(databaseSnapshot: DataSnapshot) {
                asyncToSync.signalAll(ReadDatabaseResult.Success(databaseSnapshot))
            }
        })

        when (val result = asyncToSync.await()) {
            is ReadDatabaseResult.Error -> throw result.databaseError.toException()
            is ReadDatabaseResult.Success -> {
                @Suppress("UNCHECKED_CAST")
                val value = result.databaseSnapshot.value as Map<String, Any>
                return mApiModelMapper.mapClothHashToEntity(value)
            }
        }
    }
}
