package hulkdx.com.domain

import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.domain.entities.ImageEntity
import hulkdx.com.domain.entities.UserEntity
import hulkdx.com.domain.entities.UserGender

/**
 * Created by Mohammad Jafarzadeh Rezvan on 17/07/2019.
 */

val AVATAR_IMAGE_1 = ImageEntity(
        size = 1024,
        url = "url1"
)

val CLOTH_IMAGE_1 = ImageEntity(
        size = 1025,
        url = "url2"
)
val CLOTH_IMAGE_2 = ImageEntity(
        size = 1026,
        url = "url3"
)

val TEST_USER_1  = UserEntity(
        id = "1",
        firstName = "firstName1",
        lastName = "lastName1",
        emailAddress = "email1@gmail.com",
        gender = UserGender.Male,
        image = AVATAR_IMAGE_1
)

val TEST_USER_2  = UserEntity(
        id = "2",
        firstName = "firstName2",
        lastName = "lastName2",
        emailAddress = "email2",
        gender = UserGender.Female,
        image = null
)

val TEST_CLOTH_1 = ClothEntity(
        id = "1",
        image = CLOTH_IMAGE_1,
        price = 10F,
        currency = "EURO",
        user = TEST_USER_1
)
val TEST_CLOTH_2 = ClothEntity(
        id = "2",
        image = CLOTH_IMAGE_2,
        price = 20F,
        currency = "EURO",
        user = TEST_USER_2
)

val TEST_CLOTHES = listOf(TEST_CLOTH_1, TEST_CLOTH_2)
