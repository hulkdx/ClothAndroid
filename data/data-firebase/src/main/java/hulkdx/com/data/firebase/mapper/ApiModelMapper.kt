package hulkdx.com.data.firebase.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hulkdx.com.domain.entities.*
import java.lang.reflect.Type
import java.util.*
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 01/09/2019.
 */
internal class ApiModelMapper @Inject constructor() {

    private val mGson = Gson()

    @Suppress("UNCHECKED_CAST")
    fun mapClothHashToEntity(map: Map<String, Any>): ClothesEntity {
        val collectionType = object : TypeToken<Map<String, ClothEntity>>(){}.type
        val clothes = map.toObject<Map<String, ClothEntity>>(collectionType)
        val list = clothes.values.toList()
        return ClothesEntity(list, Date())
    }

    fun mapUserHashToEntity(map: Map<String, Any>): UserEntity {
        return map.toObject(UserEntity::class.java)
    }

    private fun<T> Map<String, Any>.toObject(type: Type): T {
        val tree = mGson.toJsonTree(this)
        return mGson.fromJson(tree, type)
    }
}
