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

    override fun getClothes(): ClothesEntity {

        val apiResponse = mGson.fromJson(FakeData.FAKE_JSON, GetClothesApiModel::class.java)

        return mApiMapper.map(apiResponse)
    }

}
