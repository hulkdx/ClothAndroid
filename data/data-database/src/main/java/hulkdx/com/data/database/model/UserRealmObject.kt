package hulkdx.com.data.database.model

import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.entities.UserGender
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Mohammad Jafarzadeh Rezvan on 31/08/2019.
 */
@Suppress("MemberVisibilityCanBePrivate")
internal open class UserRealmObject(
        @PrimaryKey
        var id:                 String,
        var emailAddress:       String,
        var firstName:          String,
        var lastName:           String,
        var gender:             Int,
        var image:              ImageRealmObject?
): RealmObject() {

    // empty constructor requires by RealmObject
    @Suppress("unused")
    constructor(): this(
            "",
            "",
            "",
            "",
            0,
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
                        mapImageEntityNull(image)
                )
            }
        }
    }

    fun map(): UserEntity {

        return UserEntity(
                id = id,
                emailAddress = emailAddress,
                firstName = firstName,
                lastName = lastName,
                gender = UserGender.convert(gender),
                image = mapImageRealmObjectNull(image)
        )
    }
}
