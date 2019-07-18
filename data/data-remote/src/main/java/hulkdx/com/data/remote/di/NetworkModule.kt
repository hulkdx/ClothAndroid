package hulkdx.com.data.remote.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import hulkdx.com.data.remote.ClothApiManagerImpl
import hulkdx.com.domain.data.remote.ClothApiManager

/**
 * Created by Mohammad Jafarzadeh Rezvan on 2019-05-30.
 */
@Module
abstract class NetworkModule {

    @Binds
    abstract fun provideClothApiManager(clothApiManagerImpl: ClothApiManagerImpl): ClothApiManager
}
