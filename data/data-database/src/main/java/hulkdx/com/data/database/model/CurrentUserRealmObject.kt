package hulkdx.com.data.database.model

import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.entities.UserGender
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Mohammad Jafarzadeh Rezvan on 31/08/2019.
 */
@Suppress("MemberVisibilityCanBePrivate")
internal open class CurrentUserRealmObject(
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

    fun map(): UserEntity {

        return UserEntity(
                id = id,
                emailAddress = emailAddress,
                firstName = firstName,
                lastName = lastName,
                gender = UserGender.values()[gender],
                image = mapImageRealmObjectNull(image)
        )
    }
}

internal fun UserEntity.mapCurrentUserRealmObject(): CurrentUserRealmObject {
    return run {
        return@run CurrentUserRealmObject(
                id,
                emailAddress,
                firstName,
                lastName,
                gender.ordinal,
                mapImageEntityNull(image)
        )
    }
}
