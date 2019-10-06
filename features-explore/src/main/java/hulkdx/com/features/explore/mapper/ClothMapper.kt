package hulkdx.com.features.explore.mapper

import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.features.explore.model.Cloth
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 17/07/2019.
 */

internal class ClothMapper @Inject constructor() {

    fun mapListClothes(clothes: List<ClothEntity>): List<Cloth> {
        return clothes.map {
            return@map mapCloth(it)
        }
    }

    private fun mapCloth(cloth: ClothEntity): Cloth {
        return cloth.run { Cloth(
                imageUrl = image.url,
                price = "$price $currency"
        ) }
    }

}