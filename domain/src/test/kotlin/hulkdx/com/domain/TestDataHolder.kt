package hulkdx.com.domain

import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.domain.entities.ImageEntity
import hulkdx.com.domain.entities.UserEntity

/**
 * Created by Mohammad Jafarzadeh Rezvan on 17/07/2019.
 */

val AVATAR_IMAGE_1 = ImageEntity(200, 200, 1024, "url1")

val CLOTH_IMAGE_1 = ImageEntity(202, 202, 1025, "url2")
val CLOTH_IMAGE_2 = ImageEntity(203, 203, 1026, "url3")

val TEST_USER_1  = UserEntity(1,"username1","firstName1", "lastName1", "email1", AVATAR_IMAGE_1)
val TEST_USER_2  = UserEntity(2,"username2","firstName2", "lastName2", "email2", null)

val TEST_CLOTH_1 = ClothEntity(1, CLOTH_IMAGE_1, 10F, "EURO", TEST_USER_1)
val TEST_CLOTH_2 = ClothEntity(2, CLOTH_IMAGE_2, 20F, "EURO", TEST_USER_2)

val TEST_CLOTHES = listOf(TEST_CLOTH_1, TEST_CLOTH_2)
