package hulkdx.com.data.firebase.di

import dagger.Binds
import dagger.Module
import hulkdx.com.data.firebase.ApiManagerImpl
import hulkdx.com.domain.data.remote.GetClothesEndPoint
import hulkdx.com.domain.data.remote.RegisterEndPoint

/**
 * Created by Mohammad Jafarzadeh Rezvan on 2019-05-30.
 */
@Module
abstract class RemoteBindsModule {

    @Binds
    internal abstract fun provideGetClothesEndPoint(clothApiManagerImpl: ApiManagerImpl): GetClothesEndPoint

    @Binds
    internal abstract fun provideRegisterEndPoint(clothApiManagerImpl: ApiManagerImpl): RegisterEndPoint
}
