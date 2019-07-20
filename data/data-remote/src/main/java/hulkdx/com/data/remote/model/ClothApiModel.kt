package hulkdx.com.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */

internal data class ClothApiModel (
        @SerializedName("id")           val id:         Long,
        @SerializedName("image")        val image:      ImageApiModel,
        @SerializedName("price")        val price:      Float,
        @SerializedName("currency")     val currency:   String,
        @SerializedName("user")         val user:       UserApiModel
)