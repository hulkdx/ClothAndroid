package hulkdx.com.data.firebase.mapper

import hulkdx.com.data.firebase.model.api.ClothApiModel
import hulkdx.com.data.firebase.model.api.UserApiModel
import hulkdx.com.domain.entities.*
import java.lang.RuntimeException
import java.util.*
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 01/09/2019.
 */
internal class ApiModelMapper @Inject constructor() {

    fun mapClothToApi(clothEntity: ClothEntity): ClothApiModel {
        return clothEntity.run {
            val user = mapUserToApi(user)
            ClothApiModel(id,
                    image,
                    price.toString(),
                    currency,
                    user)
        }
    }

    private fun mapUserToApi(user: UserEntity): UserApiModel {
        return user.run {
            UserApiModel(firstName,
                    lastName,
                    emailAddress,
                    UserGender.convert(gender),
                    id)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun mapClothHashToEntity(map: Map<String, Any>): ClothesEntity {
        val clothes = mutableListOf<ClothEntity>()
        for ((id, clothValue) in map) {

            var price = 0F
            var currency = ""
            var imageEntity: ImageEntity? = null
            var userId = ""
            var userEmailAddress = ""
            var userFirstName = ""
            var userLastName = ""
            var userImage: ImageEntity? = null
            var userGender: UserGender = UserGender.Male

            for ((key, value) in clothValue as Map<String, Any>) {
                when (key) {
                    "price" -> {
                        // For some reason the value saved is sometimes Long sometimes Double etc
                        price = value.toString().toFloat()
                    }
                    "currency" -> currency = value as String
                    "image" -> {
                        imageEntity = mapImageHashToEntity(value as Map<String, Any>)
                    }
                    "user" -> {
                        for ((userKey, userValue) in value as Map<String, Any>) {
                            when (userKey) {
                                "id" -> userId = userValue as String
                                "emailAddress" -> userEmailAddress = userValue as String
                                "firstName" -> userFirstName = userValue as String
                                "lastName" -> userLastName = userValue as String
                                "image" -> userImage = mapImageHashToEntity(userValue as Map<String, Any>)
                                "gender" -> userGender = UserGender.convert(userValue.toString().toInt())
                            }
                        }
                    }
                }
            }

            if (imageEntity == null) {
                throw RuntimeException("Bug:: imageEntity == null")
            }

            val cloth = ClothEntity (
                    id          = id,
                    image       = imageEntity,
                    price       = price,
                    currency    = currency,
                    user        = UserEntity(
                            id = userId,
                            emailAddress = userEmailAddress,
                            firstName = userFirstName,
                            lastName = userLastName,
                            image = userImage,
                            gender = userGender
                    )
            )
            clothes.add(cloth)
        }
        return ClothesEntity (
                clothes=clothes,
                updatedAt = Date() // TODO:
        )
    }

    private fun mapImageHashToEntity(map: Map<String, Any>): ImageEntity? {
        var imageSize: Long?  = null
        var imageUrl: String? = null

        for ((imageKey, imageValue) in map) {
            when (imageKey) {
                "size" -> imageSize = imageValue as Long
                "url" -> imageUrl = imageValue as String
            }
        }

        if (imageSize == null || imageUrl == null) {
            return null
        }

        return ImageEntity(imageSize, imageUrl)
    }

}
