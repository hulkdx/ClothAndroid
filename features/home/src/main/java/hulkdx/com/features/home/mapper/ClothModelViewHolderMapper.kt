package hulkdx.com.features.home.mapper

import hulkdx.com.domain.model.Cloth
import hulkdx.com.features.home.model.ClothModelViewHolder
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 17/07/2019.
 */

class ClothModelViewHolderMapper @Inject constructor() {

    fun mapListClothes(clothes: List<Cloth>): List<ClothModelViewHolder> {
        return clothes.map {
            return@map mapCloth(it)
        }
    }

    fun mapCloth(cloth: Cloth): ClothModelViewHolder {
        // TODO
        return ClothModelViewHolder("")
    }
}