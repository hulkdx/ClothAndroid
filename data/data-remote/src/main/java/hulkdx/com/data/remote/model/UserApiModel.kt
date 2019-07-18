package hulkdx.com.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */
internal data class UserApiModel (
        @SerializedName("id")               val id:                 Long,
        @SerializedName("first_name")        val firstName:          String,
        @SerializedName("last_name")         val lastName:           String,
        @SerializedName("email_address")     val emailAddress:       String,
        @SerializedName("avatar_image_url")   val avatarImageUrl:     String?
)