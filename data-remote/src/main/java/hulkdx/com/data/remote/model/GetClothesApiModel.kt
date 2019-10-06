package hulkdx.com.data.remote.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Mohammad Jafarzadeh Rezvan on 19/07/2019.
 */
internal data class GetClothesApiModel (
        @SerializedName("items")        val items:          List<ClothApiModel>,
        @SerializedName("updated_at")   val updated_at:     Date
)
