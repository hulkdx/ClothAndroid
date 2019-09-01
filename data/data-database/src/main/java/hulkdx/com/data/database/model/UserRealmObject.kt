package hulkdx.com.data.database.model

import hulkdx.com.domain.entities.ImageEntity
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.entities.UserGender
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Mohammad Jafarzadeh Rezvan on 31/08/2019.
 */
internal open class UserRealmObject(
        @PrimaryKey
        var id:                 String,
        var emailAddress:       String,
        var firstName:          String,
        var lastName:           String,
        var gender:             Int,
        var imageUrl:           String?,
        var imageSize:          Long?
): RealmObject() {

    // empty constructor requires by RealmObject
    constructor(): this(
            "",
            "",
            "",
            "",
            0,
            null,
            null
    )

    companion object {
        fun map(userEntity: UserEntity): UserRealmObject {
            return userEntity.run {
                return@run UserRealmObject(
                        id,
                        emailAddress,
                        firstName,
                        lastName,
                        UserGender.convert(gender),
                        image?.url,
                        image?.size
                )
            }
        }
    }

    fun map(): UserEntity? {
        var userImageEntity: ImageEntity? = null

        imageUrl?.let { url ->
            imageSize?.let { size ->
                userImageEntity = ImageEntity(
                        url = url,
                        size = size
                )
            }
        }

        return UserEntity(
                id = id,
                emailAddress = emailAddress,
                firstName = firstName,
                lastName = lastName,
                gender = UserGender.convert(gender),
                image = userImageEntity
        )
    }
}
