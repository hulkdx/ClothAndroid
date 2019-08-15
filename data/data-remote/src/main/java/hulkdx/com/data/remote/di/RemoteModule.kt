package hulkdx.com.data.remote.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import hulkdx.com.data.remote.ClothApiManagerImpl
import hulkdx.com.data.remote.retrofit.ApiManagerRetrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Mohammad Jafarzadeh Rezvan on 2019-05-30.
 */

private const val API_BASE_URL = "https://test.test.com"

@Module
object RemoteModule {

    @Provides
    @JvmStatic
    internal fun provideGson(): Gson {
        return GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()
    }

    @Provides
    @JvmStatic
    internal fun provideApiManagerRetrofit(gson: Gson): ApiManagerRetrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return retrofit.create(ApiManagerRetrofit::class.java)
    }
}
