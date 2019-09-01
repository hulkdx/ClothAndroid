package hulkdx.com.data.firebase.model.api

import hulkdx.com.domain.entities.ImageEntity

/**
 * Created by Mohammad Jafarzadeh Rezvan on 01/09/2019.
 */
internal data class ClothApiModel (
        val id:         String,
        val image:      ImageEntity,
        val price:      String,
        val currency:   String,
        val user:       UserApiModel
)
