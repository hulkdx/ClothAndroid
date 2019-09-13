package hulkdx.com.data.database.model

import hulkdx.com.domain.entities.ClothEntity
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Mohammad Jafarzadeh Rezvan on 31/08/2019.
 */
internal open class ClothRealmObject(
        @PrimaryKey
        var id:         String,
        var image:      ImageRealmObject?,
        var price:      Float,
        var currency:   String,
        var user:       UserRealmObject?
): RealmObject() {

    // empty constructor requires by RealmObject
    @Suppress("unused")
    constructor(): this("", ImageRealmObject(0, ""), 0F, "", UserRealmObject())
}

internal fun mapClothEntity(clothEntity: ClothEntity): ClothRealmObject {
    return clothEntity.run {
        ClothRealmObject(id, mapImageEntity(image), price, currency, UserRealmObject.map(user))
    }
}

internal fun mapClothEntityList(clothEntity: List<ClothEntity>): RealmList<ClothRealmObject> {
    val result = RealmList<ClothRealmObject>()
    for (c: ClothEntity in clothEntity) {
        val realmObject = mapClothEntity(c)
        result.add(realmObject)
    }
    return result
}

internal fun mapClothRealmObject(clothRealmObject: ClothRealmObject): ClothEntity {
    return clothRealmObject.run {
        ClothEntity(id, mapImageRealmObject(image!!), price, currency, user!!.map())
    }
}

internal fun mapClothRealmObjectList(clothRealmObjectList: List<ClothRealmObject>): List<ClothEntity> {
    return clothRealmObjectList.map {
        mapClothRealmObject(it)
    }
}
