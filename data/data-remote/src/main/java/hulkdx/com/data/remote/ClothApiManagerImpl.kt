package hulkdx.com.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import hulkdx.com.data.remote.model.GetClothesApiModel
import hulkdx.com.data.remote.retrofit.ApiManagerRetrofit
import hulkdx.com.domain.data.remote.ClothApiManager
import hulkdx.com.domain.entities.ClothEntity
import hulkdx.com.domain.entities.ClothesEntity
import hulkdx.com.domain.entities.UserEntity
import javax.inject.Inject

/**
 * Created by Mohammad Jafarzadeh Rezvan on 16/07/2019.
 */

internal class ClothApiManagerImpl @Inject constructor(
        private val mGson: Gson,
        internal val mApiManagerRetrofit: ApiManagerRetrofit
): ClothApiManager {

    private val fakeJson = "{" +
            "\"items\": [\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"image_url\": \"https://ae01.alicdn.com/kf/HTB16GtKCY9YBuNjy0Fgq6AxcXXac.jpg\",\n" +
            "        \"price\": 400,\n" +
            "        \"currency\": \"Euro\",\n" +
            "        \"user\": {\n" +
            "            \"id\": 1,\n" +
            "            \"username\": \"hulk\",\n" +
            "            \"first_name\": \"Saba\",\n" +
            "            \"last_name\": \"Jafarzadeh\",\n" +
            "            \"email_address\": \"sabajafarzadeh@gmail.com\",\n" +
            "            \"avatar_image_url\": \"https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?cs=srgb&dl=beauty-bloom-blue-67636.jpg&fm=jpg\"\n" +
            "        }\n" +
            "    }\n" +
            "], \"updated_at\": \"2019-06-13 08:32:20\"" +
            "}"

    override fun getClothes(): ClothesEntity {

        val apiResponse = mGson.fromJson(fakeJson, GetClothesApiModel::class.java)

        val clothes = apiResponse.items.map {
            val user = it.user.run {
                return@run UserEntity(
                        id,
                        username,
                        firstName,
                        lastName,
                        emailAddress,
                        avatarImageUrl
                )
            }
            return@map ClothEntity(it.id, it.imageUrl, it.price, it.currency, user)
        }

        return ClothesEntity(clothes, apiResponse.updated_at)
    }

}
