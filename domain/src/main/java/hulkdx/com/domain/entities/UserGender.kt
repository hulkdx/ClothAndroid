package hulkdx.com.domain.entities

import java.lang.RuntimeException

/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */
sealed class UserGender {
    object Male: UserGender()
    object Female: UserGender()
    object Unknown: UserGender()

    companion object {
        fun convert(model: UserGender): Int {
            return when (model) {
                Male -> 0
                Female -> 1
                Unknown -> 2
            }
        }

        fun convert(value: Int): UserGender {
            return when (value) {
                0 -> Male
                1 -> Female
                2 -> Unknown
                else -> throw RuntimeException("unsupported.")
            }
        }
    }
}