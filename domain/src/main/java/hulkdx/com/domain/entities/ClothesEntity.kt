package hulkdx.com.domain.entities

import java.util.Date

/**
 * Created by Mohammad Jafarzadeh Rezvan on 19/07/2019.
 */
data class ClothesEntity (
        val clothes: List<ClothEntity>,
        val updatedAt: Date
)
