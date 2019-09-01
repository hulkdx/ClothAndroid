package hulkdx.com.domain.entities

/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */
data class ClothEntity (
        val id:         String,
        val image:      ImageEntity,
        val price:      Float,
        val currency:   String,
        val user:       UserEntity
)