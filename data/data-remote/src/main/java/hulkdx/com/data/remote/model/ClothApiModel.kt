package hulkdx.com.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */

internal data class ClothApiModel (
        @SerializedName("id")       val id:                 Long,
        @SerializedName("image_url") val imageUrl:   String,
        @SerializedName("price")    val price:      Float,
        @SerializedName("user")     val user:       UserApiModel
)