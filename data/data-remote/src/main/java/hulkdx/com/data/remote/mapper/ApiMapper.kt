package hulkdx.com.data.remote.mapper

import hulkdx.com.data.remote.model.ClothApiModel
import hulkdx.com.data.remote.model.GetClothesApiModel
import hulkdx.com.data.remote.model.ImageApiModel
import hulkdx.com.data.remote.model.UserApiModel
import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.domain.entities.ClothesEntity
import hulkdx.com.domain.entities.ImageEntity
import hulkdx.com.domain.entities.UserEntity
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 20/07/2019.
 */
internal class ApiMapper @Inject constructor() {

    fun map(apiResponse: GetClothesApiModel): ClothesEntity {
        val clothes = mapClothes(apiResponse.items)
        return ClothesEntity(clothes, apiResponse.updated_at)
    }

    private fun mapClothes(clothes: List<ClothApiModel>): List<ClothEntity> {
        return clothes.map {
            mapCloth(it)
        }
    }

    private fun mapCloth(cloth: ClothApiModel): ClothEntity {
        val image = mapImage(cloth.image)
        val user = mapUser(cloth.user)
        return ClothEntity(cloth.id, image, cloth.price, cloth.currency, user)
    }

    private fun mapUser(user: UserApiModel): UserEntity {
        return user.run {
            return@run UserEntity(
                    id = id,
                    firstName = firstName,
                    lastName = lastName,
                    emailAddress = emailAddress,
                    image = mapImageNullable(image)
            )
        }
    }

    private fun mapImageNullable(image: ImageApiModel?): ImageEntity? {
        if (image == null) return null
        return mapImage(image)
    }

    private fun mapImage(image: ImageApiModel): ImageEntity {
        return ImageEntity(image.width, image.height, image.size, image.url)
    }

}