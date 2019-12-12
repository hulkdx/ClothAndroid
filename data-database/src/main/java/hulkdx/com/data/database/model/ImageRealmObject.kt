package hulkdx.com.data.database.model

import hulkdx.com.domain.entities.ImageEntity
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Mohammad Jafarzadeh Rezvan on 31/08/2019.
 */
internal open class ImageRealmObject(
        @PrimaryKey
        var size:       Long,
        var url:        String
): RealmObject() {

    // empty constructor requires by RealmObject
    @Suppress("unused")
    constructor(): this(
            0, ""
    )
}

// region mapper -----------------------------------------------------------------------------------

internal fun mapImageEntityNull(imageEntity: ImageEntity?): ImageRealmObject? {
    if (imageEntity == null) return null
    return mapImageEntity(imageEntity)
}

internal fun mapImageEntity(imageEntity: ImageEntity): ImageRealmObject {
    return imageEntity.run {
        ImageRealmObject(size, url)
    }
}

internal fun mapImageRealmObjectNull(imageRealmObject: ImageRealmObject?): ImageEntity? {
    if (imageRealmObject == null) return null
    return mapImageRealmObject(imageRealmObject)
}

internal fun mapImageRealmObject(imageRealmObject: ImageRealmObject): ImageEntity {
    return imageRealmObject.run {
        ImageEntity(size, url)
    }
}

// end region mapper -------------------------------------------------------------------------------
