package hulkdx.com.domain.entities

/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */
data class UserEntity (
        val id:                 Long,
        val emailAddress:       String,
        val firstName:          String,
        val lastName:           String,
        val image:              ImageEntity?,
        val gender:             UserGender
)