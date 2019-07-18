package hulkdx.com.domain.entities

/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */
data class UserEntity (
        val id:                 Long,
        val firstName:          String,
        val lastName:           String,
        val emailAddress:       String,
        val avatarImageUrl:     String?
)