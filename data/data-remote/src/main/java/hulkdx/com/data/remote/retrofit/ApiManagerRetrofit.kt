package hulkdx.com.data.remote.retrofit

import hulkdx.com.data.remote.model.GetClothesApiModel
import retrofit2.http.GET

/**
 * Created by Mohammad Jafarzadeh Rezvan on 20/07/2019.
 */
internal interface ApiManagerRetrofit {

    @GET("")
    fun getClothes(): GetClothesApiModel

}