package hulkdx.com.data.firebase.database

import com.google.firebase.database.DatabaseReference
import hulkdx.com.data.firebase.mapper.ApiModelMapper
import hulkdx.com.data.firebase.util.convertListToModel
import hulkdx.com.data.firebase.util.convertToModel
import hulkdx.com.domain.entities.*
import hulkdx.com.domain.interactor.cloth.upload.UploadClothUseCase
import java.lang.RuntimeException


/**
 * Created by Mohammad Jafarzadeh Rezvan on 18/08/2019.
 */
internal class ClothDatabaseFirebase(
        private val mClothDatabase: DatabaseReference,
        private val mCategoryDatabase: DatabaseReference,
        private val mClothCategoryDatabase: DatabaseReference,
        private val mApiModelMapper: ApiModelMapper
) {

    fun addCloth(image: ImageEntity,
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

    fun addCategory(categoryId: String,
                    clothId: String,
                    onComplete: DatabaseReference.CompletionListener) {
        val id = mClothCategoryDatabase.push().key ?: throw RuntimeException("id == null")
        val clothCategoryEntity = ClothCategoryEntity(id, categoryId, clothId)
        mClothCategoryDatabase.child(id).setValue(clothCategoryEntity, onComplete)
    }

    fun findAllClothCategory(): List<ClothCategoryEntity> {
        return mClothCategoryDatabase.convertToModel { value ->
            mApiModelMapper.mapClothCategoryHashToEntity(value)
        }
    }

    fun findAll(): ClothesEntity {
        return mClothDatabase.convertToModel { value ->
            mApiModelMapper.mapClothHashToEntity(value)
        }
    }

    fun findAllCategories(): List<CategoryEntity> {
        return mCategoryDatabase.convertListToModel { value ->
            mApiModelMapper.mapCategoryHashToEntityList(value)
        }
    }
}
