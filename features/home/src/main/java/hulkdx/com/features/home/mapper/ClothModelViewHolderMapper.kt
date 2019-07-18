package hulkdx.com.features.home.mapper

import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.features.home.model.Cloth
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 17/07/2019.
 */

class ClothModelViewHolderMapper @Inject constructor() {

    fun mapListClothes(clothes: List<ClothEntity>): List<Cloth> {
        return clothes.map {
            return@map mapCloth(it)
        }
    }

    private fun mapCloth(cloth: ClothEntity): Cloth {
        // TODO
        return Cloth("")
    }
}