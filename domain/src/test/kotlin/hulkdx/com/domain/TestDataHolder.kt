package hulkdx.com.domain

import hulkdx.com.domain.model.Cloth
import hulkdx.com.domain.model.User

/**
 * Created by Mohammad Jafarzadeh Rezvan on 17/07/2019.
 */

val TEST_USER_1  = User("firstName1", "lastName1", "email1", "avatarImageUrl1")
val TEST_USER_2  = User("firstName2", "lastName2", "email2", null)

val TEST_CLOTH_1 = Cloth("imageUrl1", 10F, TEST_USER_1)
val TEST_CLOTH_2 = Cloth("imageUrl2", 20F, TEST_USER_2)

val TEST_CLOTHES = listOf(TEST_CLOTH_1, TEST_CLOTH_2)
