package hulkdx.com.data.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import hulkdx.com.data.remote.model.ClothApiModel
import hulkdx.com.domain.data.remote.ClothApiManager
import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.domain.entities.UserEntity
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */

class ClothApiManagerImpl @Inject constructor(
): ClothApiManager {

    private val mGson = Gson()
    private val fakeJson = "[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"image_url\": \"https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?cs=srgb&dl=beauty-bloom-blue-67636.jpg&fm=jpg\",\n" +
            "        \"price\": 400,\n" +
            "        \"currency\": \"Euro\",\n" +
            "        \"user\": {\n" +
            "            \"id\": 1,\n" +
            "            \"first_name\": \"Saba\",\n" +
            "            \"last_name\": \"Jafarzadeh\",\n" +
            "            \"email_address\": \"sabajafarzadeh@gmail.com\",\n" +
            "            \"avatar_image_url\": \"https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?cs=srgb&dl=beauty-bloom-blue-67636.jpg&fm=jpg\"\n" +
            "        }\n" +
            "    }\n" +
            "]"

    override fun getCloths(): List<ClothEntity> {

        val collectionType = object : TypeToken<List<ClothApiModel>>() {}.type
        val apiResponse = mGson.fromJson<List<ClothApiModel>>(fakeJson, collectionType)

        return apiResponse.map {
            val user = it.user.run {
                return@run UserEntity(
                        id,
                        firstName,
                        lastName,
                        emailAddress,
                        avatarImageUrl
                )
            }
            return@map ClothEntity(it.id, it.imageUrl, it.price, it.currency, user)
        }
    }

}
