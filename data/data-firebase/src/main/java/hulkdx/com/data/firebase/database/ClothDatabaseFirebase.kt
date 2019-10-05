package hulkdx.com.data.firebase.database

import com.google.firebase.database.DatabaseReference
import hulkdx.com.data.firebase.mapper.ApiModelMapper
import hulkdx.com.data.firebase.util.convertToModel
import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.domain.entities.ClothesEntity
import hulkdx.com.domain.entities.ImageEntity
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCase
import java.lang.RuntimeException


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
        mClothDatabase.child(id).setValue(clothEntity, onComplete)
        return clothEntity
    }

    fun findAll(): ClothesEntity {
        return mClothDatabase.convertToModel { value ->
            mApiModelMapper.mapClothHashToEntity(value)
        }
    }
}
