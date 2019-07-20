package hulkdx.com.data.remote

import com.google.gson.Gson
import hulkdx.com.data.remote.mapper.ApiMapper
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
        private val mApiMapper: ApiMapper,
        private val mGson: Gson, // TODO delete this
        private val mApiManagerRetrofit: ApiManagerRetrofit
): ClothApiManager {

    private val fakeJson = "{\n" +
            "    \"items\": [\n" +
            "        {\n" +
            "            \"id\": 1,\n" +
            "            \"image\": {\n" +
            "                \"width\": 500,\n" +
            "                \"height\": 500,\n" +
            "                \"size\": 1024,\n" +
            "                \"url\": \"https://ae01.alicdn.com/kf/HTB16GtKCY9YBuNjy0Fgq6AxcXXac.jpg\"\n" +
            "            },\n" +
            "            \"price\": 400,\n" +
            "            \"currency\": \"Euro\",\n" +
            "            \"user\": {\n" +
            "                \"id\": 1,\n" +
            "                \"username\": \"hulk\",\n" +
            "                \"first_name\": \"Saba\",\n" +
            "                \"last_name\": \"Jafarzadeh\",\n" +
            "                \"email_address\": \"sabajafarzadeh@gmail.com\",\n" +
            "                \"image\": {\n" +
            "                    \"width\": 500,\n" +
            "                    \"height\": 500,\n" +
            "                    \"size\": 1024,\n" +
            "                    \"url\": \"https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?cs=srgb&dl=beauty-bloom-blue-67636.jpg&fm=jpg\"\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    ],\n" +
            "    \"updated_at\": \"2019-06-13 08:32:20\"\n" +
            "}\n"

    override fun getClothes(): ClothesEntity {

        val apiResponse = mGson.fromJson(fakeJson, GetClothesApiModel::class.java)

        return mApiMapper.map(apiResponse)
    }

}
