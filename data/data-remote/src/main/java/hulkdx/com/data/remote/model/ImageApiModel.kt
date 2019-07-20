package hulkdx.com.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mohammad Jafarzadeh Rezvan on 20/07/2019.
 *
 *
 */
data class ImageApiModel(
        @SerializedName("width")    val width:      Int,
        @SerializedName("height")   val height:     Int,
        @SerializedName("size")     val size:       Long,
        @SerializedName("url")      val url:        String
)