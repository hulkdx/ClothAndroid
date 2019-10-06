package hulkdx.com.data.database.model

import hulkdx.com.domain.entities.*
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 31/08/2019.
 */
internal open class ClothesRealmObject(
        var clothes: RealmList<ClothRealmObject>,
        var updatedAt: Date
): RealmObject() {

    @Suppress("unused")
    @PrimaryKey
    private var id: Int = 0

    // empty constructor requires by RealmObject
    @Suppress("unused")
    constructor(): this(
            RealmList(),
            Date()
    )
}

internal fun mapClothesEntity(clothesEntity: ClothesEntity): ClothesRealmObject {
    return clothesEntity.run {
        ClothesRealmObject(mapClothEntityList(clothes), updatedAt)
    }
}

internal fun mapClothesRealmObject(realmObject: ClothesRealmObject): ClothesEntity {
    return realmObject.run {
        ClothesEntity(mapClothRealmObjectList(clothes), updatedAt)
    }
}
